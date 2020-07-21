package co.edu.escuelaing.arsw.bombermanparty.endpoints;

import java.io.IOException;
import java.util.logging.Level;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

import co.edu.escuelaing.arsw.bombermanparty.aplicacion.Bomba;
import co.edu.escuelaing.arsw.bombermanparty.aplicacion.BombermanParty;
import co.edu.escuelaing.arsw.bombermanparty.aplicacion.Fijo;
import co.edu.escuelaing.arsw.bombermanparty.aplicacion.Jugador;
import co.edu.escuelaing.arsw.bombermanparty.aplicacion.Sala;
import co.edu.escuelaing.arsw.bombermanparty.aplicacion.Temporal;
import co.edu.escuelaing.arsw.bombermanparty.exeptions.BombermanPartyException;

@Component
@ServerEndpoint("/bbService")
public class BBEndpoint {

    private static final Logger logger = Logger.getLogger(BBEndpoint.class.getName());
    /* Queue for all open WebSocket sessions */
    static Queue<Session> queue = new ConcurrentLinkedQueue<>();

    // SessionRepo sessionRepo
    // =(SessionRepo)BBApplicationContextAware.getApplicationContext().getBean("sessionRepo");
    BombermanParty bp = BombermanParty.getInstance();
    Session ownSession = null;


    


    @OnMessage
    public void processPoint(String message, Session session) {
        // System.out.println("MENSAJE "+message+" "+conectado);
        String[] msg = message.split(" ");
        logger.log(Level.INFO, "Point received:" + message + ". From session: " + session);
        if ("addJugador".equals(msg[0])) {
            Integer id = Integer.parseInt(msg[1]);
            try {
                bp.crearSala(id);
                bp.agregarJugador(id, msg[2], session);
                //getTemporales(session, id);
                //getFijos(session, id);
                //moverJugadores(id);
            } catch (BombermanPartyException e) {
                try {
                    session.getBasicRemote().sendText("est/La sala está llena");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }else if("mov".equals(msg[0])){
            Integer id = Integer.parseInt(msg[1]);
            Integer x = Integer.parseInt(msg[3]);
            Integer y = Integer.parseInt(msg[4]);
            bp.moverJugador(id, msg[2], x, y);
            //moverJugadores(id);
        }else if("bom".equals(msg[0])){
            Integer id = Integer.parseInt(msg[1]);
            bp.ponerBomba(id, msg[2]);
            
        }

    }

    @OnOpen
    public void openConnection(Session session) {
        /* Register this connection in the queue */

        queue.add(session);
        ownSession = session;
        logger.log(Level.INFO, "Connection opened.");
        try {
            session.getBasicRemote().sendText("Connection established.");

        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    @OnClose
    public void closedConnection(Session session) {
        /* Remove this connection from the queue */
        // sessionRepo.removeSession(session);
        bp.quitarJugador(session);
        queue.remove(session);
        
        logger.log(Level.INFO, "Connection closed for session " + session);
    }

    @OnError
    public void error(Session session, Throwable t) {
        /* Remove this connection from the queue */
        // sessionRepo.removeSession(session);
        //bp.quitarJugador(session.getId());
        queue.remove(session);
        
        //Logger.getLogger(BBEndpoint.class.getName()).log(Level.SEVERE, null, t);
        logger.log(Level.INFO, t.toString());
        logger.log(Level.INFO, "Connection error.");
    }
}
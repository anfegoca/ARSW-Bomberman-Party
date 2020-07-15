package co.edu.escuelaing.arsw.bombermanparty.aplicacion;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.websocket.Session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.escuelaing.arsw.bombermanparty.exeptions.BombermanPartyException;

public class Sala {
    private int id;
    private Escenario escenario;
    //private List<String> sessions = new ArrayList<>();
    private Hashtable<Session, String> sessions;

    public Sala(int id) {
        sessions = new Hashtable<>();
        this.id = id;
        this.escenario = new Escenario(this);
    }

    public void agregarJugador(String nombre, Session session) throws BombermanPartyException {
        sessions.put(session,nombre);
        escenario.agregarJugador(nombre);
        actualizarObjetos("Fijo", escenario.getFijos());
        actualizarObjetos("Temp", escenario.getTemporales());
        actualizarObjetos("Jug", escenario.getJugadores());
    }
    public void quitarJugador(Session session){
        System.out.println("Sala: "+session);
        String name = sessions.get(session);
        sessions.remove(session);
        escenario.quitarJugador(name);

    }
    public void actualizarObjetos(String enc, List<?> lista){
        try {
            ObjectMapper map = new ObjectMapper();
            //List<Fijo> fijos = escenario.getFijos();
            String json = map.writeValueAsString(lista);
            for(Session s: sessions.keySet()){
                s.getBasicRemote().sendText(enc+"/" + json);
            }
        } catch (JsonProcessingException ex) {
            System.out.println(ex);
        } catch (IOException e) {
            System.out.println(e);
        }
    }


	public List<Temporal> getTemporales() {
		return escenario.getTemporales();
	}

	public List<Fijo> getFijos() {
		return escenario.getFijos();
	}

	public List<Jugador> getJugadores() {
		return escenario.getJugadores();
	}

	public void moverJugador(String nombre, int x, int y) {
        escenario.moverJugador(nombre, x, y);
        actualizarObjetos("Jug", escenario.getJugadores());
	}

	public void ponerBomba(String nombre) {
        System.out.println("SALA");
        escenario.ponerBomba(nombre);
        actualizarObjetos("Bomb",escenario.getBombas());
    }
    public List<Bomba> getBombas(){
        return escenario.getBombas();
    }
    


}
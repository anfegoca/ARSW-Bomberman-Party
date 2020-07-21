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
    private Hashtable<Session, String> sessions;

    public Sala(int id) {
        sessions = new Hashtable<>();
        this.id = id;
        this.escenario = new Escenario(this);
    }

    /**
     * Agrega un jugador a la sala con el nombre dado
     * @param nombre del jugador que se quiere agragar
     * @param session del jugador que se quiere agregar
     * @throws BombermanPartyException Si el escenario ya tiene los 4 jugadores
     */
    public void agregarJugador(String nombre, Session session) throws BombermanPartyException {
        sessions.put(session,nombre);
        escenario.agregarJugador(nombre);
        actualizarObjetos("Fijo", escenario.getFijos());
        actualizarObjetos("Temp", escenario.getTemporales());
        actualizarObjetos("Jug", escenario.getJugadores());
    }
    /**
     * Quita el jugador dado de la sala
     * @param session del jugador el cual va a salir de la sala
     */
    public void quitarJugador(Session session){
        String name = sessions.get(session);
        sessions.remove(session);
        escenario.quitarJugador(name);

    }
    /**
     * Informa al cliente para que actualice los objetos dados
     * @param enc de los objetos los cuales quiere actualizar
     * @param lista de objetos que quiere actualizar
     */
    public synchronized void actualizarObjetos(String enc, List<?> lista){
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
    /**
     * Mueve el jugador dado a la posicion x y y dadas
     * @param nombre del jugador de la sala que se quire mover
     * @param x posición en x a la cual se quiere mover
     * @param y posición en y a la cual se quiere mover
     */
    public void moverJugador(String nombre, int x, int y) {
        escenario.moverJugador(nombre, x, y);
        actualizarObjetos("Jug", escenario.getJugadores());
    }
    /**
     * Hace que el jugador dado coloque una bomba esu posicióna actual
     * @param nombre del jugador el cual quire poner la bomba
     */
    public void ponerBomba(String nombre) {
        
        escenario.ponerBomba(nombre);
        actualizarObjetos("Bomb",escenario.getBombas());
    }
    public Escenario obtEsc(){
        return escenario;
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
	
    public List<Bomba> getBombas(){
        return escenario.getBombas();
    }

	
    


}
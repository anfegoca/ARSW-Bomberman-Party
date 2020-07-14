package co.edu.escuelaing.arsw.bombermanparty.aplicacion;

import java.util.Hashtable;
import java.util.List;


import javax.websocket.Session;

import co.edu.escuelaing.arsw.bombermanparty.exeptions.BombermanPartyException;

public class Sala {
    private int id;
    private Escenario escenario;
    //private List<String> sessions = new ArrayList<>();
    private Hashtable<String, String> sessions;

    public Sala(int id) {
        sessions = new Hashtable<>();
        this.id = id;
        this.escenario = new Escenario();
    }

    public void agregarJugador(String nombre, Session session) throws BombermanPartyException {
        sessions.put(session.getId(),nombre);
        escenario.agregarJugador(nombre);
    }
    public void quitarJugador(String session){
        System.out.println("Sala: "+session);
        String name = sessions.get(session);
        sessions.remove(session);
        escenario.quitarJugador(name);

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
	}
    public Hashtable<String,String> getSessions(){
        return sessions;
    }
    


}
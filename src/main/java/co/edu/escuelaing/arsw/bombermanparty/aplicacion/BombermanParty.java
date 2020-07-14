package co.edu.escuelaing.arsw.bombermanparty.aplicacion;

import java.util.Hashtable;
import java.util.List;

import javax.websocket.Session;

import co.edu.escuelaing.arsw.bombermanparty.exeptions.BombermanPartyException;

/**
 *
 * @author Andres Gonzalez
 */

public class BombermanParty {
    
    private Hashtable<Integer, Sala> salas;
    
    
    private BombermanParty(){
        salas = new Hashtable<>();
        crearSala(1);
    }

    private static class helper {
        private static final BombermanParty INSTANCE = new BombermanParty();
    }

    public static BombermanParty getInstance() {
        return helper.INSTANCE;
    }

    /**
     * 
     */
    public void crearSala(int codigo){
        Sala sala = new Sala(codigo);
        salas.put(codigo, sala);
    }
    /**
     * Agrega un jugador al escenario
     * @param nombre nombre del jugadore
     * @throws BombermanPartyException Si no hay espacio en el escenario (mas de 4 jugadores)
     */
    public void agregarJugador(int codigo,String nombre,Session session) throws BombermanPartyException{
        Sala sala = salas.get(codigo);
        sala.agregarJugador(nombre,session);
    }
    public void quitarJugador(String session){
        for(Sala s: salas.values()){
            s.quitarJugador(session);
        }
        
    }
    /**
     * Retorna los bloques Temporales del escenario
     * @return List<Temporal> lista de bloques temporales
     */
    public List<Temporal> getTemporales(int codigo){
        Sala sala = salas.get(codigo);
        return sala.getTemporales();
    }
    /**
     * Retorna los bloques Fijos del escenario
     * @return List<fijo> lista de bloques fijos
     */
    public List<Fijo> getFijos(int codigo){
        Sala sala = salas.get(codigo);
        return sala.getFijos();
    }
    /**
     * Obtiene la lista de jugadores del Escenario dado
     * @param codigo codigo del escenario
     * @return lista de jugadores
     */
    public List<Jugador> getJugadores(int codigo){
        Sala sala = salas.get(codigo);
        return sala.getJugadores();
    }
    public void moverJugador(int codigo,String nombre,int x, int y){
        Sala sala = salas.get(codigo);
        sala.moverJugador(nombre, x, y);
    }
    public Sala getSala(int id){
        return salas.get(id);
    }
    
    
    
    
    
}

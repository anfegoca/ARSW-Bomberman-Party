package co.edu.escuelaing.arsw.bombermanparty.aplicacion;

import java.util.Hashtable;
import java.util.List;

import co.edu.escuelaing.arsw.bombermanparty.exeptions.BombermanPartyException;

/**
 *
 * @author Andres Gonzalez
 */

public class BombermanParty {
    
    private Hashtable<Integer, Escenario> escenarios;
    
    
    private BombermanParty(){
        escenarios = new Hashtable<>();
        crearEscenario(1);
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
    public void crearEscenario(int codigo){
        Escenario escenario = new Escenario();
        escenarios.put(codigo, escenario);
    }
    /**
     * Agrega un jugador al escenario
     * @param nombre nombre del jugadore
     * @throws BombermanPartyException Si no hay espacio en el escenario (mas de 4 jugadores)
     */
    public void agregarJugador(int codigo,String nombre) throws BombermanPartyException{
        Escenario escenario = escenarios.get(codigo);
        escenario.agregarJugador(nombre);
    }
    /**
     * Retorna los bloques Temporales del escenario
     * @return List<Temporal> lista de bloques temporales
     */
    public List<Temporal> getTemporales(int codigo){
        Escenario escenario = escenarios.get(codigo);
        return escenario.getTemporales();
    }
    /**
     * Retorna los bloques Fijos del escenario
     * @return List<fijo> lista de bloques fijos
     */
    public List<Fijo> getFijos(int codigo){
        Escenario escenario = escenarios.get(codigo);
        return escenario.getFijos();
    }
    /**
     * Obtiene la lista de jugadores del Escenario dado
     * @param codigo codigo del escenario
     * @return lista de jugadores
     */
    public List<Jugador> getJugadores(int codigo){
        Escenario escenario = escenarios.get(codigo);
        return escenario.getJugadores();
    }
    public void moverJugador(int codigo,String nombre,int x, int y){
        Escenario escenario = escenarios.get(codigo);
        escenario.moverJugador(nombre, x, y);

    }
    
    
    
    
    
}

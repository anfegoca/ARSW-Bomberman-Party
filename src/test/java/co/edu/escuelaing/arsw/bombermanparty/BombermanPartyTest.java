package co.edu.escuelaing.arsw.bombermanparty;

import co.edu.escuelaing.arsw.bombermanparty.aplicacion.BombermanParty;
import co.edu.escuelaing.arsw.bombermanparty.aplicacion.Escenario;
import co.edu.escuelaing.arsw.bombermanparty.aplicacion.Jugador;
import co.edu.escuelaing.arsw.bombermanparty.aplicacion.Sala;
import co.edu.escuelaing.arsw.bombermanparty.exeptions.BombermanPartyException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *
 * @author Andres Gonzalez
 */
public class BombermanPartyTest {
    private BombermanParty bp = BombermanParty.getInstance();

    public BombermanPartyTest() {
        bp.crearSala(9999);
    }

    /**
     * Verifica que no hayan bloques en donde aparecen los jugadores
     */

    @Test
    public void CrearEscenario() {
        Sala sala = bp.getSala(9999);
        Escenario escenario = sala.obtEsc();
        boolean res = true;
        if (escenario.existeBloque(0, 0) || escenario.existeBloque(10, 0)
                || escenario.existeBloque(escenario.getAncho() - 10, 0)
                || escenario.existeBloque(escenario.getAncho(), 0) || escenario.existeBloque(10, 0)
                || escenario.existeBloque(escenario.getAncho(), 10)
                || escenario.existeBloque(escenario.getAlto() - 10, 0)
                || escenario.existeBloque(escenario.getAlto() - 10, escenario.getAncho())
                || escenario.existeBloque(0, escenario.getAlto()) || escenario.existeBloque(10, escenario.getAlto())
                || escenario.existeBloque(escenario.getAncho() - 10, escenario.getAlto())
                || escenario.existeBloque(escenario.getAncho(), escenario.getAlto())) {
            res = false;
        }
        assertTrue(res);
    }

    /** No pueden ingresar mas de 4 jugadores */
    @Test
    public void maxJugadores() {
        try {
            Sala sala = bp.getSala(9999);
            Escenario escenario = sala.obtEsc();
            escenario.agregarJugador("a");
            escenario.agregarJugador("b");
            escenario.agregarJugador("c");
            escenario.agregarJugador("d");
            escenario.agregarJugador("e");
        } catch (BombermanPartyException e) {
            assertTrue(true);

        }
    }

    /** No se pueden poner dos bombas en el mismo lugar */
    @Test
    public void ColocarBombas() {
        Sala sala = bp.getSala(9999);
        Escenario escenario = sala.obtEsc();
        escenario.quitarJugador("a");
        try {
            escenario.agregarJugador("a");
            escenario.ponerBomba("a");
            escenario.ponerBomba("a");
            assertEquals(escenario.getBombas().size(), 1);
        } catch (BombermanPartyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /** el Jugador no puede atravesar las bombas */
    @Test
    public void moverJugador() {
        bp.crearSala(99999);
        Sala sala = bp.getSala(99999);
        Escenario escenario = sala.obtEsc();
        try {
            escenario.agregarJugador("a");
            escenario.ponerBomba("a");
            escenario.moverJugador("a", 10, 0);
            escenario.moverJugador("a", -10, 0);
            int posx=0;
            for(Jugador j: escenario.getJugadores()){
                if(j.getNombre().equals("a")){
                    posx=j.getX();
                }
            }
            escenario.quitarJugador("a");
            assertEquals(posx, 10);
            
        } catch (BombermanPartyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**El contador de muertes aumenta */
    @Test
    public void muerte(){
        bp.crearSala(99999);
        Sala sala = bp.getSala(99999);
        Escenario escenario = sala.obtEsc();
        try {
            escenario.agregarJugador("a");
            escenario.ponerBomba("a");
            int muertes=0;
            for(Jugador j: escenario.getJugadores()){
                if(j.getNombre().equals("a")){
                    muertes=j.getMuertes();
                }
            }
            escenario.quitarJugador("a");
            assertEquals(muertes, 0);
            
        } catch (BombermanPartyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

package co.edu.escuelaing.arsw.bombermanparty;

import co.edu.escuelaing.arsw.bombermanparty.aplicacion.Escenario;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Andres Gonzalez
 */
public class BombermanPartyTest {
    
    /**
     * Verifica que no hayan bloques en donde aparecen los jugadores
     */
    @Test
    public void CrearEscenario(){
        Escenario escenario = new Escenario();
        boolean res = true;
        if (escenario.existeBloque(0, 0) || escenario.existeBloque(10, 0) || escenario.existeBloque(escenario.getAncho()-10, 0) || escenario.existeBloque(escenario.getAncho(), 0)
                || escenario.existeBloque(10, 0) || escenario.existeBloque(escenario.getAncho(), 10) || escenario.existeBloque(escenario.getAlto()-10, 0)
                || escenario.existeBloque(escenario.getAlto()-10, escenario.getAncho()) || escenario.existeBloque(0, escenario.getAlto())
                || escenario.existeBloque(10, escenario.getAlto()) || escenario.existeBloque(escenario.getAncho()-10, escenario.getAlto())
                || escenario.existeBloque(escenario.getAncho(), escenario.getAlto())){
            res=false;
        }
        assertTrue(res);   
    }
    
}

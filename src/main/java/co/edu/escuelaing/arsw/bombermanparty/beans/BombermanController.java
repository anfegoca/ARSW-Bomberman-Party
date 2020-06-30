package co.edu.escuelaing.arsw.bombermanparty.beans;

import co.edu.escuelaing.arsw.bombermanparty.aplicacion.BombermanParty;
import co.edu.escuelaing.arsw.bombermanparty.aplicacion.Fijo;
import co.edu.escuelaing.arsw.bombermanparty.aplicacion.Jugador;
import co.edu.escuelaing.arsw.bombermanparty.aplicacion.Temporal;
import co.edu.escuelaing.arsw.bombermanparty.exeptions.BombermanPartyException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andres Gonzalez
 */
@RestController
public class BombermanController {
    private BombermanParty bp = BombermanParty.getInstance();

    @GetMapping("/getFijos")
    public String getFijos(@RequestParam(value = "codigo") int codigo) {
        try {
            ObjectMapper map = new ObjectMapper();
            List<Fijo> fijos = bp.getFijos(codigo);
            String json = map.writeValueAsString(fijos);

            return json;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(BombermanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @GetMapping("/getTemporales")
    public String getTemporales(@RequestParam(value = "codigo") int codigo) {
        try {
            ObjectMapper map = new ObjectMapper();
            List<Temporal> temporales = bp.getTemporales(codigo);
            String json = map.writeValueAsString(temporales);

            return json;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(BombermanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @PostMapping("/addJugador")
    public void agregarJugador(@RequestParam(value = "nombre") String nombre,@RequestParam(value = "codigo") int codigo) {
        try {
            bp.agregarJugador(codigo,nombre);
        } catch (BombermanPartyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @GetMapping("/getJugadores")
    public String getJugadores(@RequestParam(value = "codigo") int codigo){
        try {
            ObjectMapper map = new ObjectMapper();
            List<Jugador> jugadores = bp.getJugadores(codigo);
            String json = map.writeValueAsString(jugadores);
            return json;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(BombermanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    @PostMapping("/moverJugador")
    public void moverJugador(@RequestParam(value = "nombre") String nombre,@RequestParam(value = "codigo") int codigo,@RequestParam(value = "x")int x,@RequestParam(value = "y")int y){
        bp.moverJugador(codigo, nombre, x, y);
    }
}

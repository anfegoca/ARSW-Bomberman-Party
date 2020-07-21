package co.edu.escuelaing.arsw.bombermanparty.controllers;

import co.edu.escuelaing.arsw.bombermanparty.aplicacion.BombermanParty;

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

    
    @PostMapping("/nuevaSala")
    public boolean moverJugador(@RequestParam(value = "codigo") String codigo){
        Integer x = Integer.parseInt(codigo);
        return bp.crearSala(x);
    }
    
}

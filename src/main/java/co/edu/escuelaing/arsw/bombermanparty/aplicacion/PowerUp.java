package co.edu.escuelaing.arsw.bombermanparty.aplicacion;

import java.awt.Rectangle;

/**
 *
 * @author Andres Gonzalez
 */
public abstract class PowerUp {
    protected int x;
    protected int y;
    protected int ancho;
    protected int alto;
    protected Rectangle collider;

    public PowerUp(int x, int y) {
        this.alto=10;
        this.ancho=10;
        this.x = x;
        this.y = y;
        this.collider=new Rectangle(x,y,ancho,alto);
    }

    public abstract boolean choca(Jugador jugador);
    

    
}

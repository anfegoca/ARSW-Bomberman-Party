package co.edu.escuelaing.arsw.bombermanparty.aplicacion;
import java.awt.Rectangle;
/**
 *
 * @author Andres Gonzalez
 */
public class Bloque {
    protected int x;
    protected int y;
    protected int ancho;
    protected int alto;
    protected Rectangle collider;
    
    public Bloque(int x,int y){
        ancho=10;
        alto=10;
        this.x=x;
        this.y=y;
        collider = new Rectangle(x,y,ancho,alto);
    }
    /**
     * Verifica si el bloque tiene la misma posicion que la dad
     * @param x posicion en x
     * @param y posicion en y
     * @return boolean true si la posicion es la misma
     */
    public boolean posActual(int x,int y){
        if(this.x==x && this.y==y){
            return true;
        }else{
            return false;
        }
    }
    /**
     * Verifica si el collider de un objeto dado colisiona con el del bloque
     * @param c Rectangle del cual se quiere comprobar la colision
     * @return boolean true si colisionan
     */
    public boolean choca(Rectangle c){
        if(collider.intersects(c)){
            return true;
        }else{
            return false;
        }

    }

    
}

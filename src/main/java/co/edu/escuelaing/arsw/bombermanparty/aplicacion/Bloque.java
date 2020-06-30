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
    public boolean posActual(int x,int y){
        if(this.x==x && this.y==y){
            return true;
        }else{
            return false;
        }
    }
    public boolean choca(Rectangle c){
        if(collider.intersects(c)){
            return true;
        }else{
            return false;
        }

    }

    
}

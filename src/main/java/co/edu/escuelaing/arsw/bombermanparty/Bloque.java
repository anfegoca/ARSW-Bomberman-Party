package co.edu.escuelaing.arsw.bombermanparty;

/**
 *
 * @author Andres Gonzalez
 */
public class Bloque {
    protected int x;
    protected int y;
    protected int ancho;
    protected int alto;
    
    public Bloque(int x,int y){
        ancho=10;
        alto=10;
        this.x=x;
        this.y=y;
    }
    public boolean posActual(int x,int y){
        if(this.x==x && this.y==y){
            return true;
        }else{
            return false;
        }
    }
}

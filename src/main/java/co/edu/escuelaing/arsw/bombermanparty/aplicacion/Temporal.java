package co.edu.escuelaing.arsw.bombermanparty.aplicacion;

/**
 *
 * @author Andres Gonzalez
 */
public class Temporal extends Bloque{
    public Temporal(int x,int y){
        super(x,y);
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getAncho(){
        return this.ancho;
    }
    public int getAlto(){
        return this.alto;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
}

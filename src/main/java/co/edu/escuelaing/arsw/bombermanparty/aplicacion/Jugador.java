package co.edu.escuelaing.arsw.bombermanparty.aplicacion;
import java.awt.Rectangle;
/**
 *
 * @author Andres Gonzalez
 */
public class Jugador {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int muertes;
    private int puntos;
    private String nombre;
    private int velocidad;
    private Escenario escenario;
    private Rectangle collider;

    public Jugador(Escenario escenario, String nombre, int x,int y) {
        this.escenario = escenario;
        this.nombre = nombre;
        this.x=x;
        this.y=y;
        this.alto=10;
        this.ancho=10;
        this.velocidad=1;
        collider = new Rectangle(x,y,ancho,alto);
        muertes=0;
        puntos=0;
    }
    public void move(int x,int y){
        int newX = this.x + x*velocidad;
        int newY = this.y + y*velocidad;
        collider.translate(x*velocidad, y*velocidad);
        if(!escenario.colision(collider) && escenario.inEscenario(collider)){
            this.x=newX;
            this.y=newY;
        }else{
            collider.translate(-x*velocidad, -y*velocidad);
        }
        
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMuertes() {
        return muertes;
    }

    public void setMuertes(int muertes) {
        this.muertes = muertes;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
    

    

    
}

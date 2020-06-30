package co.edu.escuelaing.arsw.bombermanparty.aplicacion;

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

    public Jugador(String nombre, int x,int y) {
        this.nombre = nombre;
        this.x=x;
        this.y=y;
        this.alto=10;
        this.ancho=10;
        muertes=0;
        puntos=0;
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
    

    

    
}

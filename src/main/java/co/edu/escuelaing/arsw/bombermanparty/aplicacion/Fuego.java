package co.edu.escuelaing.arsw.bombermanparty.aplicacion;
import java.awt.Rectangle;


public class Fuego {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private Rectangle collider;
    private char tipo;
    private String dueño;
    

    public Fuego(int x,int y, char tipo,String dueño){
        this.dueño=dueño;
        this.tipo=tipo;
        this.x=x;
        this.y=y;
        ancho=10;
        alto=10;
        collider = new Rectangle(x,y,ancho,alto);
    }
    public boolean choca(Escenario escenario){
        boolean res = false;
        if(escenario.inEscenario(collider) && !escenario.collisionFijo(collider)){
            res = true;
        }
        return res;
    }
    public Rectangle Obtcollider(){
        return collider;
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

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getDueño() {
        return dueño;
    }

    public void setDueño(String dueño) {
        this.dueño = dueño;
    }

    
}
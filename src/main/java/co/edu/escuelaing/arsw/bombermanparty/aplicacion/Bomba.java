package co.edu.escuelaing.arsw.bombermanparty.aplicacion;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Andres Gonzalez
 */
public class Bomba {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int impacto;

    
    public Bomba(int x, int y, int impacto){
        this.x=x;
        this.y=y;
        this.impacto=impacto;
        ancho = 10;
        alto = 10;
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

    public int getImpacto() {
        return impacto;
    }

    public void setImpacto(int impacto) {
        this.impacto = impacto;
    }
    
    
}

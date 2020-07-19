package co.edu.escuelaing.arsw.bombermanparty.aplicacion;

import java.awt.Rectangle;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
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
    private int poder;
    private int numBombas;
    private int xi;
    private int yi;

    public Jugador(Escenario escenario, String nombre, int x,int y) {
        this.escenario = escenario;
        this.nombre = nombre;
        this.xi=x;
        this.yi=y;
        this.x=x;
        this.y=y;
        this.alto=10;
        this.ancho=10;
        this.velocidad=1;
        collider = new Rectangle(x,y,ancho,alto);
        muertes=0;
        puntos=0;
        poder=1;
        numBombas=1;
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
        escenario.reclamarSorpresa(this);
        
    }
    public Bomba ponerBomba() {
        if(numBombas>0){
            numBombas--;
            Bomba bomba = new Bomba(x,y,poder,escenario,nombre);
            Timer timer = new Timer();
            TimerTask task = new TimerTask(){
                @Override
                public void run() {
                    bomba.explote();
                    escenario.explote(bomba);
                    numBombas++;
                    
                }
            };
            timer.schedule(task, 3000);;
           return  bomba;
        }else{
            return null;
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
    public boolean choca(Rectangle r){
        return collider.intersects(r);
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
	public void muerase() {
        muertes++;
        x=xi;
        y=yi;
        collider = new Rectangle(x,y,ancho,alto);
    }
    public void aumentarPoder(){
        poder++;
    }
    public void aumentarBombas(){
        numBombas++;
    }
	
    

    

    
}

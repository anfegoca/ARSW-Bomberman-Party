package co.edu.escuelaing.arsw.bombermanparty.aplicacion;

import java.awt.Rectangle;
import java.util.ArrayList;
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
    private boolean explosion;
    private Escenario escenario;
    private ArrayList<Fuego> fuegos;
    private String dueño;
    private Rectangle collider;

    
    public Bomba(int x, int y, int impacto, Escenario escenario,String nombre){
        this.dueño=nombre;
        this.escenario=escenario;
        this.x=x;
        this.y=y;
        this.impacto=impacto;
        ancho = 10;
        alto = 10;
        explosion=false;
        fuegos = new ArrayList<>();
        collider = new Rectangle(x,y,ancho,alto);
    }

    public void explote(){
        Fuego fuego = new Fuego(x, y, 'C',dueño);
        fuegos.add(fuego);
        escenario.addFuego(fuego);
        escenario.SumarPuntos(fuego);
        int[] posx = { -10, 0, 10, 0 };
        int[] posy = { 0, 10, 0, -10 };
        ArrayList<Integer> lados = new ArrayList<>();
        for (int i = 0; i < impacto; i++) {
            for (int j = 0; j < 4; j++) {
                if(!lados.contains(j)){

                if(j%2==0){
                     fuego = new Fuego(x+posx[j]*(i+1), y+posy[j]*(i+1),'H',dueño);
                }else{
                     fuego = new Fuego(x+posx[j]*(i+1), y+posy[j]*(i+1),'V',dueño);
                }
                
                if(fuego.choca(escenario)){
                    fuegos.add(fuego);
                    escenario.addFuego(fuego);
                    escenario.destruirBloque(fuego);
                    escenario.SumarPuntos(fuego);
                }else{
                    lados.add(j);
                }
            }
                
            }
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

    public boolean isExplosion() {
        return explosion;
    }

    public void setExplosion(boolean explosion) {
        this.explosion = explosion;
    }

    public ArrayList<Fuego> getFuegos() {
        return fuegos;
    }

    public void setFuegos(ArrayList<Fuego> fuegos) {
        this.fuegos = fuegos;
    }

	public boolean choca(Rectangle collider) {
		return this.collider.intersects(collider);
	}
    
    
}

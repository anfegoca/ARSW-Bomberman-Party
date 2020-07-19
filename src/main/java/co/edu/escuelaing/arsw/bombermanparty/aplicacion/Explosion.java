package co.edu.escuelaing.arsw.bombermanparty.aplicacion;

public class Explosion extends PowerUp {

    public String tipo;

    public Explosion(int x, int y) {
        super(x, y);
        tipo=this.getClass().getSimpleName();
    }

    @Override
    public boolean choca(Jugador jugador) {
        boolean res = false;
        if(jugador.choca(collider)){
            jugador.aumentarPoder();
            res = true;
        }
        return res;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    
}
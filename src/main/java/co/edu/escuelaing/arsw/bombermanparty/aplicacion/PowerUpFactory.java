package co.edu.escuelaing.arsw.bombermanparty.aplicacion;

public class PowerUpFactory {
    public PowerUp create(PowerUps tipo,int x,int y){
        PowerUp res = null;
        switch(tipo){
            case Explosion:
                res = new Explosion(x, y);
                break;
            case Municion:
                res = new Municion(x,y);
                break;
        }
        return res;
    }
    
}
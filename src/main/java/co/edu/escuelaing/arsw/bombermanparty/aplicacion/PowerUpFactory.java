package co.edu.escuelaing.arsw.bombermanparty.aplicacion;

public class PowerUpFactory {
    /**
     * Crea un PowerUp del tipo dado y lo retorna
     * @param tipo del powerUp
     * @param x posicion en x en la cual se quiere crear
     * @param y posición en y en la cual se quiere crear
     * @return PowerUp creado
     */
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
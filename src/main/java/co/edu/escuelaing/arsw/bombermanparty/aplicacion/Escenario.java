package co.edu.escuelaing.arsw.bombermanparty.aplicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Rectangle;

import co.edu.escuelaing.arsw.bombermanparty.exeptions.BombermanPartyException;

/**
 *
 * @author Andres Gonzalez
 */
public class Escenario {
    private Sala sala;
    private int ancho;
    private int alto;
    private ArrayList<Bloque> bloques;
    private ArrayList<Fijo> fijos;
    private ArrayList<Temporal> temporales;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Bomba> bombas;
    private Rectangle collider;
    private ArrayList<Fuego> fuegos;
    private PowerUpFactory fabrica;
    private ArrayList<PowerUp> powerUps;

    public Escenario(Sala sala) {
        this.sala = sala;
        ancho = 200;
        alto = 200;
        bloques = new ArrayList<>();
        fijos = new ArrayList<>();
        temporales = new ArrayList<>();
        jugadores = new ArrayList<>();
        bombas = new ArrayList<>();
        fuegos = new ArrayList<>();
        powerUps = new ArrayList<>();
        collider = new Rectangle(0, 0, ancho + 10, alto + 10);
        fabrica=new PowerUpFactory();
        prepareBloquesFijos();
        prepareBloquesTemporales();

    }

    /**
     * Crea los bloques fijos
     */
    private void prepareBloquesFijos() {

        for (int i = 10; i < 200; i += 20) {
            for (int j = 10; j < 200; j += 20) {
                Fijo bloque = new Fijo(i, j);
                bloques.add(bloque);
                fijos.add(bloque);
            }
        }
    }

    /**
     * Crea los bloques temporales
     */
    private void prepareBloquesTemporales() {
        for (int i = 0; i < ancho + 10; i += 10) {
            for (int j = 0; j < alto + 10; j += 10) {
                Random random = new Random();
                if (!existeBloque(i, j) && (i != 0 && i != 10 && i != ancho - 10 && i != ancho)
                        && (j != 0 && j != 10 && j != alto - 10 && j != alto)) {
                    if (random.nextBoolean()) {
                        Temporal bloque = new Temporal(i, j);
                        bloques.add(bloque);
                        temporales.add(bloque);
                    }
                }
            }
        }
    }

    /**
     * Comprueba si existe un bloque en la posición dada
     * 
     * @param x posición en eje x
     * @param y posición en eje y
     * @return true si hay un bloque en la posición dada
     */
    public boolean existeBloque(int x, int y) {
        boolean res = false;
        for (Bloque b : bloques) {
            if (b.posActual(x, y)) {
                res = true;
                break;
            }
        }
        return res;
    }

    /**
     * Agrega un jugador al escenario
     * 
     * @param nombre Nomber del jugador a agregar
     * @throws BombermanPartyException Si ya está lleno el escenario (4 jugadores)
     */
    public void agregarJugador(String nombre) throws BombermanPartyException {
        Jugador jugador = null;
        if (jugadores.size() == 0) {
            jugador = new Jugador(this, nombre, 0, 0);
        } else if (jugadores.size() == 1) {
            jugador = new Jugador(this, nombre, ancho, 0);
        } else if (jugadores.size() == 2) {
            jugador = new Jugador(this, nombre, 0, alto);
        } else if (jugadores.size() == 3) {
            jugador = new Jugador(this, nombre, ancho, alto);
        } else {
            throw new BombermanPartyException(BombermanPartyException.JUGADORES_COMPLETOS);
        }
        jugadores.add(jugador);
    }

    /**
     * Mueve al jugador dado su nombre en la dirección dada
     * 
     * @param nombre nombre del jugador
     * @param x      posición en x
     * @param y      posición en y
     */
    public void moverJugador(String nombre, int x, int y) {
        for (Jugador j : jugadores) {
            if (j.getNombre().equals(nombre)) {
                j.move(x, y);
            }
        }

    }

    public boolean inEscenario(Rectangle collider) {
        return this.collider.contains(collider);
    }

    public boolean colision(Rectangle collider) {
        boolean res = false;
        for (Bloque b : bloques) {
            if (b.choca(collider)) {
                res = true;
                break;
            }
        }
        for(Bomba bo: bombas){
            if(bo.choca(collider)){
                res=true;
                break;
            }
        }
        return res;
    }
    public boolean collisionFijo(Rectangle collider){
        boolean res = false;
        for(Fijo f: fijos){
            if(f.choca(collider)){
                res = true;
                break;
            }
        }
        return res;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public List<Fijo> getFijos() {
        return fijos;
    }

    public List<Temporal> getTemporales() {
        return temporales;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void quitarJugador(String nombre) {
        for (Jugador j : jugadores) {
            if (j.getNombre().equals(nombre)) {
                jugadores.remove(j);
                break;
            }
        }
    }

    public void ponerBomba(String nombre) {
        for (Jugador j : jugadores) {
            if (j.getNombre().equals(nombre)) {
                Bomba bomba = j.ponerBomba();
                if (bomba != null) {
                    bombas.add(bomba);
                }
                break;
            }
        }
    }
    

    public List<Bomba> getBombas() {
        return bombas;
    }

    public void setBombas(ArrayList<Bomba> bombas) {
        this.bombas = bombas;
    }
    public void generarPowerUp(int x,int y){
        Random r = new Random();
        int num = r.nextInt(4);
        if(num==0){
            PowerUps[] tipos = PowerUps.values();
            int sor = r.nextInt(tipos.length);
            System.out.println("Se genero "+tipos[sor]);
            powerUps.add(fabrica.create(tipos[sor], x, y));
        }
    }

    public void destruirBloque(Fuego fuego) {
        // System.out.println("X: "+x+" Y: "+y );
        for (Temporal t : temporales) {
            if (t.choca(fuego.Obtcollider())) {
                temporales.remove(t);
                bloques.remove(t);
                generarPowerUp(t.getX(), t.getY());
                break;
            }
        }
        
    }
    public void SumarPuntos(Fuego fuego){
        for(Jugador j: jugadores){
            if (j.choca(fuego.Obtcollider())) {
                j.muerase();
                sala.actualizarObjetos("Jug", getJugadores());
                if(!fuego.getDueño().equals(j.getNombre())){
                    for(Jugador jug: jugadores){
                        if(fuego.getDueño().equals(jug.getNombre())){
                            jug.setPuntos(jug.getPuntos()+1);
                        }
                    }

                }
                
                
            }
        }
    }

    public void explote(Bomba bomba) {
        // System.out.println("BOMB");
        // sala.explote(bombas.indexOf(bomba));
        sala.actualizarObjetos("Fueg", getFuegos());
        bombas.remove(bomba);
        sala.actualizarObjetos("Bomb", getBombas());
        sala.actualizarObjetos("Temp", getTemporales());
        Timer timer = new Timer();
        TimerTask t = new TimerTask(){

            @Override
            public void run() {
                fuegos.removeAll(bomba.getFuegos());
                sala.actualizarObjetos("Fueg", getFuegos());

            }
            
        };
        timer.schedule(t, 500);
        sala.actualizarObjetos("Pow",getPowerUps());
        


    }
    public void addFuego(Fuego fuego){
        fuegos.add(fuego);

    }

    public ArrayList<Fuego> getFuegos() {
        return fuegos;
    }

    public void setFuegos(ArrayList<Fuego> fuegos) {
        this.fuegos = fuegos;
    }

	public void reclamarSorpresa(Jugador jugador) {
        for(PowerUp p: powerUps){
            if(p.choca(jugador)){
                powerUps.remove(p);
                break;
            }
            
        }
        sala.actualizarObjetos("Pow", getPowerUps());
    }

    public ArrayList<PowerUp> getPowerUps() {
        return powerUps;
    }

    public void setPowerUps(ArrayList<PowerUp> powerUps) {
        this.powerUps = powerUps;
    }
    

}

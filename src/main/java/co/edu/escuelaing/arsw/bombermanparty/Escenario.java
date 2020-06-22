package co.edu.escuelaing.arsw.bombermanparty;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Andres Gonzalez
 */
public class Escenario {
    
    private int ancho;
    private int alto;
    private ArrayList<Bloque> bloques;
    private ArrayList<Fijo> fijos;
    private ArrayList<Temporal> temporales;
    
    public Escenario(){
        ancho=160;
        alto=160;
        bloques = new ArrayList<>();
        fijos = new ArrayList<>();
        temporales = new ArrayList<>();
        prepareBloquesFijos();
        prepareBloquesTemporales();
        
    }
    /**
     * Crea los bloques fijos
     */
    private void prepareBloquesFijos(){
        for(int i=10;i<160;i+=20){
            for(int j=10;j<160;j+=20){
                Fijo bloque=new Fijo(i,j);
                bloques.add(bloque);
                fijos.add(bloque);
            }
        }
    }
    /**
     * Crea los bloques temporales
     */
    private void prepareBloquesTemporales(){
        for(int i=0;i<ancho+10;i+=10){
            for(int j=0;j<alto+10;j+=10){
                Random random = new Random();
                if(!existeBloque(i,j) && (i!=0 && i!=10 && i!=ancho-10 && i!=ancho) && (j!=0 && j!=10 && j!=alto-10 && j!=alto )){
                    if(random.nextBoolean()){
                        Temporal bloque = new Temporal(i,j);
                        bloques.add(bloque);
                        temporales.add(bloque);
                    }
                }
            }
        }
    }
    /**
     * Comprueba si existe un bloque en la posición dada
     * @param x posición en eje x
     * @param y posición en eje y
     * @return true si hay un bloque en la posición dada
     */
    public boolean existeBloque(int x,int y){
        boolean res = false;
        for(Bloque b: bloques){
            if(b.posActual(x, y)){
                res= true;
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
    
}

package co.edu.escuelaing.arsw.bombermanparty.exeptions;


public class BombermanPartyException extends Exception{
    public static String JUGADORES_COMPLETOS = "Ya hay 4 jugadores en el escenario";

    public BombermanPartyException(String message) {
        super(message);
    }
    
}


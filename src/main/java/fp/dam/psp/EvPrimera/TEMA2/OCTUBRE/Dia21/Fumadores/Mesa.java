package fp.dam.psp.EvPrimera.TEMA2.OCTUBRE.Dia21.Fumadores;

import java.util.HashSet;
import java.util.Set;

public class Mesa {
    //Una manera seria esta
    /*

    boolean papel = false;
    boolean tabaco = false;
    boolean cerillas = false;
     */

    //Otra manera
    Set<String> Ingredientes = new HashSet<>();


    public synchronized void colIn (String i1, String i2){
        if (Ingredientes.isEmpty()){
            try {
                wait();
                //! Nos va a mandar hacer que esta ⤵️ salte
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());

            }
        }
    }

}

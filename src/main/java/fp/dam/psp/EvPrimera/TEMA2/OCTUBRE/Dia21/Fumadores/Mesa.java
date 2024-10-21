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
    Set<String> ingredientes = new HashSet<>();


    public synchronized void colIn (String i1, String i2){
        if (!ingredientes.isEmpty()){
            try {
                wait();
                //! Nos va a mandar hacer que esta ⤵️ salte
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        ingredientes.add(i1);
        ingredientes.add(i2);
        System.out.println("> El agente agrego los ingredientes: " + i1 + " y " + i2);
        notifyAll(); //Se notifican todos los hilos
    }

    //! Se puede realizar de dos formas, con void no va a retornar nada. No es del to-do necesario, ya que es vaciar el bloque del Set.
    public  synchronized void reIn (String ingre){
        while ( ingredientes.isEmpty() || ingredientes.contains(ingre)){
            System.out.println("> El fumador en espera");
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        ingredientes.clear();
        notifyAll();

    }

}

package fp.dam.psp.EvPrimera.Other.Fumadores;

import static fp.dam.psp.EvPrimera.Other.Fumadores.Main.actualizar;

import java.util.HashSet;
import java.util.Set;

public class Mesa {
    Set<Ingredientes> ingredientes = new HashSet<>();

    public synchronized void poner (Ingredientes i1, Ingredientes i2){
        if (!ingredientes.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        ingredientes.add(i1);
        ingredientes.add(i2);
        actualizar("> El agente coloco: " + i1 + "\n");
        actualizar("> El agente coloco: " + i2 + "\n");
        notifyAll();

    }

    public synchronized void coger (Ingredientes ingre){
        while (ingredientes.isEmpty() || ingredientes.contains(ingre)) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        ingredientes.clear();
        actualizar("> El fumador retiro ingrediente: " + ingre + "\n");
        actualizar("> El fumador se puso a fumar\n");
        notifyAll();

    }
}

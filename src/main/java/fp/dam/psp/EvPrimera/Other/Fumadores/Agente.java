package fp.dam.psp.EvPrimera.Other.Fumadores;

import static fp.dam.psp.EvPrimera.TEMA2.OCTUBRE.Dia21.Fumadores.AnotherVersion.Main.actualizar;

public class Agente extends Thread {
    Mesa mesa;
    static boolean s = false; // Suspendido
    static boolean f = false; // matar hilo

    public Agente(Mesa mesa) {
        super("Agente");
        this.mesa = mesa;
    }

    // ! ======= METODOS PARA FINALIZAR LOS HILOS =====
    public synchronized void suspender() {
        s = true;
    }

    public synchronized void reanudar() {
        s = false;
        notify();
    }

    public synchronized void fin() {
        f = true;
        interrupt();
    }

    @Override
    public void run() {
        while (!f) {
            synchronized (this) {
                if (s) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Ingredientes i1 = Ingredientes.get();
            Ingredientes i2 = Ingredientes.get();
			while(i1==i2)
				i2 = Ingredientes.get();
			mesa.poner(i1, i2);
			actualizar ("El agente finaliza su tarea\n");
        }
    }

}
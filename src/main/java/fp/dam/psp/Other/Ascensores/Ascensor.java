package fp.dam.psp.Other.Ascensores;

import static fp.dam.psp.Other.Ascensores.Main.actualizar;

public class Ascensor extends Thread {
    String nombre;
    int Actual = 0;
    static boolean s = false; // Suspendido
    static boolean f = false; // matar hilo

    public Ascensor(String nombre) {
        this.nombre = nombre;
    }

    // ! ======= METODOS PARA FINALIZAR LOS HILOS =====
    public synchronized void suspender() {
        s = true;
    }

    public synchronized void reanudar() {
        s = false;
        notifyAll();
    }

    public synchronized void fin() {
        f = true;
        interrupt();
    }

    @Override
    public void run() {
        while (!f) {
            synchronized(this){
                while (s) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

            //! La logica siempre va aqui
            moverA();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
    }

    private void moverA() {
        actualizar(nombre + " se mueve al piso " + (Actual + 1) + "\n");
        Actual++;
        if (Actual > 20) {
            Actual = 0;
        }
    }
}

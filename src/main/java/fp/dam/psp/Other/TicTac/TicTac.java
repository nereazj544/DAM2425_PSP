package fp.dam.psp.Other.TicTac;

import static fp.dam.psp.Other.TicTac.Main.actualizar;

public class TicTac extends Thread {
    String sonido;
    static boolean s = false; // Suspendido
    static boolean f = false; // matar hilo

    public TicTac(String sonido) {
        this.sonido = sonido;
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
        while (!f) { // Mientras no se pida finalizar el hilo
            synchronized (this) {
                while (s) { // Si está suspendido, espera
                    try {
                        wait(); // Suspende el hilo
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Lógica de sonido
            actualizar(sonido + "\n");

            try {
                sleep(1000); // Pausa de 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

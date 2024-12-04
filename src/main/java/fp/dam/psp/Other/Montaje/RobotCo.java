package fp.dam.psp.Other.Montaje;

import java.util.Random;

public class RobotCo extends Thread {
    Cadena cadena;
    Random r = new Random();
    static boolean s = false; // Suspendido
    static boolean f = false; // matar hilo

    public RobotCo(Cadena cadena) {
        this.cadena = cadena;
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
        try {
            while (!f) {
                synchronized (this) {
                    while (s) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                int tipo = r.nextInt(3);
                cadena.colcar(tipo);
                sleep(r.nextInt(1000) + 500);

            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}

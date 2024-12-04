package fp.dam.psp.Other.Barbero;

import static fp.dam.psp.Other.Barbero.Main.actualizar;

public class Barbero extends Thread {
    Barberia barberia;

    static boolean s = false; // Suspendido
    static boolean f = false; // matar hilo
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

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
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

                Cliente cliente = barberia.atenderCliente();
                actualizar("Atendiendo a cliente" + cliente.getId() + "\n");
                sleep(1000);
                actualizar("Cliente " + cliente.getId() + " ha sido atendido\n");

            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}

package fp.dam.psp.Other.Globos;

import static fp.dam.psp.Other.Globos.Main.actualizar;

public class HinchaGlobos extends Thread {
    Almacen almacen;
    String nombre;
    static boolean s = false; // Suspendido
    static boolean f = false; // matar hilo

    public HinchaGlobos(Almacen almacen, int id) {
        this.almacen = almacen;
        this.nombre = "HG" + id;
    }

    // ! ======= METODOS PARA FINALIZAR LOS HILOS =====
    public synchronized void suspender() {
        s = true;
    }

    public synchronized void reanudar() {
        s = false;
        // notifyAll();
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
                while (s) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            if (almacen.hayGlobosDisponibles()) {
                int num = almacen.entregar(nombre);
                almacen.EmpHinchar();
                int vl = 0;
                while (vl < 5 && !f) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    vl++;
                    actualizar("GLOBO " + num + " VL " + vl + "\n");
                    if (Math.random() < 0.2) {
                        actualizar("GLOBO " + num + " EXPLOTO \n");
                    }
                }
                if (!f && vl == 5) {
                    actualizar("GLOBO " + num + " HINZHADO \n");
                }
                almacen.finHinchar();
            } else {
                break;
            }
        }
    }

}

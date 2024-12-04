package fp.dam.psp.Other.Globos;

import static fp.dam.psp.Other.Globos.Main.actualizar;

import java.util.Random;

public class PincharGlobos extends Thread{
    Almacen almacen;
    String nombre;
    static boolean s = false; // Suspendido
    static boolean f = false; // matar hilo
    Random r = new Random();

    public PincharGlobos(Almacen almacen, int id) {
        this.almacen = almacen;
        this.nombre = "PG" + id;
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
            synchronized (this){
                while (s) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            if (almacen.hayGlobosDisponibles() || almacen.hayGlobosHinchando()) {
                try {
                    sleep(r.nextInt(9000) + 1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (almacen.hayGlobosDisponibles()) {
                    actualizar("GLOBO PINCHADO POR " + nombre + "\n");
                }
            }else{
                break;
            }
        }
    }

    
}

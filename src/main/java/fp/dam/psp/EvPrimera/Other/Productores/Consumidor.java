package fp.dam.psp.EvPrimera.Other.Productores;
import static fp.dam.psp.EvPrimera.Other.Productores.Main.actualizar;
/**
 * Consumidor
 */
public class Consumidor extends Thread {
    private Almacen almacen;
    static boolean s = false; // Suspendido
    static boolean f = false; // matar hilo
    long r = 0;
    public Consumidor(Almacen almacen, long r) {
        super("Consumidor");
        this.almacen = almacen;
        this.r = r;
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
            synchronized(this){
                try {
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        String prodcuto = almacen.retirar();
        actualizar("prodcuto " + prodcuto + " retirado");
        try {
            sleep(r);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
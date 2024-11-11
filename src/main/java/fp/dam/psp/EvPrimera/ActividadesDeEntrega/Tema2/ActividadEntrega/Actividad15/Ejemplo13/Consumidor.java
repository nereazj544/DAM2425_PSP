package fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad15.Ejemplo13;
import static  fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad15.Ejemplo13.Main.actualizar;
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
        notifyAll();
    }

    public synchronized void fin() {
        f = true;
        interrupt();
    }

    @Override
public void run() {
    while (!f) {
        synchronized(this) {
            while (s) { // Revisión de suspensión
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        // Retirar productos mientras no esté suspendido ni finalizado
        String producto = almacen.retirar();
        actualizar("Producto " + producto + " retirado \n");

        try {
            sleep(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

}
package fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad15.Ejemplo13;

import static fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad15.Ejemplo13.Main.actualizar;

public class Prodructor extends Thread{
    long contador, r = 0;
    private Almacen almacen;
    static boolean s = false; //Suspendido
    static boolean f = false; //matar hilo
    public Prodructor(long r, Almacen almacen) {
        super("Productor");
        this.r = r;
        this.almacen = almacen;
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
        // Añadir productos mientras no esté suspendido ni finalizado
        String producto = String.format("%d", ++contador);
        almacen.almacenar(producto);
        actualizar("Producto " + producto + " añadido\n");

        try {
            sleep(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
    
}

package fp.dam.psp.EvPrimera.Other.Productores;

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
        notify();
    }

    public synchronized void fin() {
        f = true;
        interrupt();
    }
}

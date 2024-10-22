package fp.dam.psp.EvPrimera.Other.Productores;

import static fp.dam.psp.EvPrimera.Other.Productores.Main.actualizar;

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
        String prodcuto = String.format("%d", ++contador);
        almacen.almacenar(prodcuto);
        actualizar("prodcuto " + prodcuto + " añadido");
        try {
            sleep(r);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}

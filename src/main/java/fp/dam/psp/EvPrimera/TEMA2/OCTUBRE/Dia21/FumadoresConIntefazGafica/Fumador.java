package fp.dam.psp.EvPrimera.TEMA2.OCTUBRE.Dia21.FumadoresConIntefazGafica;

import static fp.dam.psp.EvPrimera.TEMA2.OCTUBRE.Dia21.Fumadores.AnotherVersion.Main.actualizar;

public class Fumador extends Thread{
    Ingredientes ingredientes;
    Mesa mesa;
    static boolean s = false; //Suspendido
    static boolean f = false; //matar hilo

    public Fumador(String name, Ingredientes ingredientes, Mesa mesa) {
        super(name);
        this.ingredientes = ingredientes;
        this.mesa = mesa;
    }

    //! ======= METODOS PARA FINALIZAR LOS HILOS =====
    public synchronized void suspender(){
        s = true;
    }

    public synchronized void reanudar(){
        s = false;
        notify();
    }

    public synchronized void fin(){
        f = true;
        interrupt();
    }

    @Override
    public void run() {
        while (!f) {
            synchronized(this){
                if (s) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            mesa.coger(ingredientes);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            actualizar(getName() + " termino de fumar \n");
        }
    }

    
    
    
}

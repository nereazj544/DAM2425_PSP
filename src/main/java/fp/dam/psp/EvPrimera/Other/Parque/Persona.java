package fp.dam.psp.EvPrimera.Other.Parque;

public class Persona extends Thread {
    int id;
    Banco banco;
    static boolean s = false; // Suspendido
    static boolean f = false; // matar hilo

    public Persona(int id, Banco banco) {
        this.id = id;
        this.banco = banco;
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




    
}

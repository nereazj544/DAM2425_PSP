package fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad15.Ejemplo12;

import java.util.concurrent.locks.ReentrantLock;

public class Contador {
    ReentrantLock lock = new ReentrantLock();
    int n;

    public Contador(int n) {
        this.n = n;
    }

    public void inc() {
        lock.lock();
        try {
            n++;
            // ! Punto critico
        } finally {
            lock.unlock();
        }
    }

    public int get(){
        return n;
    }
}

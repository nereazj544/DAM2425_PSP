package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.N.Lock;

import java.util.concurrent.locks.*;

public class ConLock extends Thread {
    static final int N = 5;

    Lock l = new ReentrantLock();
    int hilos = 0;
    int id;
    int mimir;

    public ConLock(int id, int mimir) {
        this.id = id;
        this.mimir = mimir;
    }

    @Override
    public void run() {
        try {
            l.lock();
            System.out.println("> Hilo " + id + " iniciando. Dormira: " + mimir + " ms");

        } finally {
            l.unlock();
        }

        try {
            Thread.sleep(mimir);
        } catch (Exception e) {
            // TODO: handle exception
        }
        l.lock();

        try {
            System.out.println("Hilo " + id +
                    " finalizado");
            hilos++;

            if (hilos == N) {
                System.out.println("Todos los hilos " +
                        "han terminado");
            }
        } finally {
            l.unlock();
        }
    }

    public static void main(String[] args) {
        Thread [] h = new Thread[N];
        for (int i = 0; i < N; i++) {
            h[i] = new Thread(
                new ConLock(i, (i+1)*1000)
            );
            h[i].start();
            
        }
    }

}

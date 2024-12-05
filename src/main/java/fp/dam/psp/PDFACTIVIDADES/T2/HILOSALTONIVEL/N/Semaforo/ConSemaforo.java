package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.N.Semaforo;

import java.util.concurrent.Semaphore;

public class ConSemaforo extends Thread {

    static final int N = 5;

    static Semaphore s = new Semaphore(1);
    static int h = 0;

    int id;
    int mimir;

    public ConSemaforo(int id, int mimir) {
        this.id = id;
        this.mimir = mimir;
    }

    @Override
    public void run() {
        try {
            s.acquire();
            System.out.println("> Hilo " + id + " iniciando. Mimira " +  mimir + " ms");
            s.release();

            Thread.sleep(mimir);

            s.release();
            
            System.out.println("> Hilo " + id + " finalizando");
            h++;
            
            if (h == N) {
                System.out.println("> Todos los hilos " + " han termiado");
            }
            s.release();
            
        } catch (Exception e) {
            // TODO: handle exception
            e.getMessage();
        }
    }


    public static void main(String[] args) {
        Thread[] h = new Thread[N];
        for (int i = 0; i < N; i++) {
            //! SIN EL THREAD NO SE HACE NADA :v
            h[i] = new Thread (new ConSemaforo(i, (i+1)*1000));

            h[i].start();
        }
    }
}

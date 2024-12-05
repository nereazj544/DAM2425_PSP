package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.TT.Lock;

import java.util.concurrent.locks.*;

public class ConLock extends Thread {
    // Semaphore s = new Semaphore(1);
    Lock l = new ReentrantLock();
    String sonido;

    public ConLock(String sonido) {
        this.sonido = sonido;
    }

    @Override
    public void run() {
        while (true) {
            l.lock();
            try {

                try {
                    System.out.println(sonido);

                } finally {
                    l.unlock();
                }

                Thread.sleep(1000);

            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    public static void main(String[] args) {
        Thread tic = new Thread(new ConLock("TIC"));
        Thread tac = new Thread(new ConLock("TAC"));
        tic.start();
        tac.start();
    }

}

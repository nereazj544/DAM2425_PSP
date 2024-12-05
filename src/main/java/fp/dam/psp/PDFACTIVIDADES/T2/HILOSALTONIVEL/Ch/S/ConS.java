package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.Ch.S;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ConS {
    private static final int N_Fl = 5;
    static Random r = new Random();
    private static final Semaphore[] pl = new Semaphore[N_Fl];
    private static final Semaphore s = new Semaphore(N_Fl - 1);

    static class Filosofos extends Thread {
        int id;
        Semaphore plIz;
        Semaphore plDch;

        public Filosofos(int id) {
            this.id = id;
            this.plIz = pl[id];
            this.plDch = pl[(id + 1) % N_Fl];
        }

        private void pensar() throws InterruptedException {
            System.out.println("> Filosofo " + id + " pensando");
            Thread.sleep(r.nextInt(1000));
        }

        private void comer() throws InterruptedException {
            System.out.println("> Filosofo " + id + " comiedno");
            Thread.sleep(r.nextInt(1000));

        }

        @Override
        public void run() {
            try {
                while (true) {
                    pensar();

                    s.acquire();
                    plIz.acquire();
                    System.out.println("> Filosofo " + id + " palillo Iz");
                    plDch.acquire();
                    System.out.println("> Filosofo " + id + " palillo drch");
                    comer();
                    plDch.acquire();
                    System.out.println("> Filosofo " + id + " solto palillo drch");
                    plIz.acquire();
                    System.out.println("> Filosofo " + id + " solto palillo Iz");
                    s.release();

                }
            } catch (Exception e) {
                // TODO: handle exception
                interrupt();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < N_Fl; i++) {
            pl[i] = new Semaphore(15); //Numero de hilso
        }

        Thread[] fl = new Thread[N_Fl];
        for (int i = 0; i < N_Fl; i++) {
            fl[i] = new Thread(new Filosofos(i));
            fl[i].start();
        }
    }
}

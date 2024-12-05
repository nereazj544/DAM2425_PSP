package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.Ch.L;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class ConL {
    
    private static final int N_Fl = 5;
    static Random r = new Random();
    private static final Lock[] pl = new Lock[N_Fl];
    
    
    static class Filosofos extends Thread {
        int id;
        Lock plIz;
        Lock plDch;

        public Filosofos(int id) {
            this.id = id;
            this.plIz = pl[id];
            this.plDch = pl[(id + 1) % N_Fl];
        }

        private void pensar() throws InterruptedException  {
            System.out.println("> Filosofo " + id+ " pensando");
            Thread.sleep(r.nextInt(1000));
        }
        
        private void comer()  throws InterruptedException {
            System.out.println("> Filosofo " + id+ " comiedno");
            Thread.sleep(r.nextInt(1000));

        }

        @Override
        public void run() {
            try {
                while (true) {
                    pensar();


                    plIz.lock();
                    System.out.println("> Filosofo " + id + " palillo Iz");
                    plDch.lock();
                    System.out.println("> Filosofo " + id + " palillo drch");
                    comer();
                    plDch.lock();
                    System.out.println("> Filosofo " + id + " solto palillo drch");
                    plIz.lock();
                    System.out.println("> Filosofo " + id + " solto palillo Iz");
                }
            } catch (Exception e) {
                interrupt();
            }
        }

    }

    public static void main(String[] args) {
        for (int i = 0; i < N_Fl; i++) {
            pl[i] = new ReentrantLock();
        }

        Thread[] fl = new Thread[N_Fl];
        for (int i = 0; i < N_Fl; i++) {
            fl[i] = new Thread(new Filosofos(i));
            fl[i].start();
        }
    }

}

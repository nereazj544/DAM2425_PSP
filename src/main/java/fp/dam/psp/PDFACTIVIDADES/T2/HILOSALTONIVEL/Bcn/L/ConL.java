package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.Bcn.L;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.*;

public class ConL {
    private static int N_plz;
    private static int N_per;
    private static int plzOcu = 0;

    private static Lock l = new ReentrantLock();
    private static Condition plzLibr = l.newCondition();
    private static Random r = new Random();

    static class Persona extends Thread {
        int id;

        public Persona(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("> Persona " + id + " empieza a pasear");

                    Thread.sleep(1000 + r.nextInt(2000));

                    l.lock();

                    try {
                        while (plzOcu >= N_plz) {
                            System.out.println("> Persona " + id + " esperando");

                            plzLibr.await();
                        }
                        plzOcu++;
                    } finally {
                        l.unlock();
                    }

                    Thread.sleep(100 + r.nextInt(600));
                    l.lock();

                    try {
                        plzOcu--;

                        System.out.println("> Persona " + id + " se va. Plaza Ocupada: " + plzOcu);
                        plzLibr.signal();

                    } finally {
                        l.unlock();
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("> NUMERO PLAZAS: ");
        N_plz = sc.nextInt();
        System.out.print("> NUMERO PERSONAS: ");
        N_per = sc.nextInt();

        Thread[] pr = new Thread[N_per];
        for (int i = 0; i < N_per; i++) {
            pr[i] = new Thread(new Persona(i));
            pr[i].start();
        }
        
    }

}

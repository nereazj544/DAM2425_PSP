package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.Bcn.S;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class CoS {
    private static int N_plz;
    private static int N_per;

    private static Semaphore plz;

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
                    System.out.println("> Persona " + id + " intenta sentar el culo");
                    plz.acquire();

                    System.out.println("> Persona " + id + " se sienta. Plazas dis: " + plz.availablePermits());
                    Thread.sleep(100 +  r.nextInt(600));
                    
                    plz.release();
                    System.out.println("> Persona " + id + " se va. Plazas dis: " + plz.availablePermits());

                } catch (Exception e) {
                    // TODO: handle exception
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
        sc.close();

    }
}

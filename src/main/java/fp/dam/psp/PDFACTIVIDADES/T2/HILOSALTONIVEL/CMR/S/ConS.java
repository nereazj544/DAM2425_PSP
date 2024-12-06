package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.CMR.S;

import java.util.*;
import java.util.concurrent.*;

public class ConS {
    static final int MAX_Prd = 10;
    static final int[] cadena = new int[MAX_Prd];

    static int tlEm = 0;
    static int posAc = 0;

    static final Semaphore esDis = new Semaphore(MAX_Prd);
    private static final Semaphore s = new Semaphore(1);
    private static final Semaphore hayPro = new Semaphore(0);

    private static Random r = new Random();

    static class RobotCl extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    esDis.acquire();
                    s.acquire();

                    int t = r.nextInt(3) + 1;
                    cadena[posAc++] = t;
                    System.out.println("Colocado prodcuto tipo: " + t);

                    s.release();
                    hayPro.release();

                    sleep(r.nextInt(1000));

                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }

    }

    static class RobotEm extends Thread {
        private int tEs;

        public RobotEm(int tipo) {
            this.tEs = tipo;
        }

        @Override
        public void run() {
            try {
                hayPro.acquire();
                s.acquire();

                boolean en = false;

                for (int i = 0; i < posAc; i++) {
                    if (cadena[i] == tEs) {
                        System.out.println("> Robot " + tEs + " empaqueta producto");

                        for (int j = 0; j < posAc - 1; j++) {
                            cadena[j] = cadena[j + 1];
                        }
                        posAc--;
                        tlEm++;
                        System.out.println("> Total empaquetados: " + tlEm);

                        en = true;
                    }
                }
                s.release();
                if (!en) {
                    hayPro.release();

                } else {
                    esDis.release();
                }
                sleep(r.nextInt(1000));
            } catch (Exception e) {
                // TODO: handle exception
            }

        }

    }

    public static void main(String[] args) {
        Thread col = new Thread(new RobotCl());
        Thread [] em = new Thread[3];

        for (int i = 0; i < 3; i++) {
            em[i] = new Thread(new RobotEm(i+1));
        }

        col.start();

        for (Thread thread : em) {
            thread.start();
        }
    }

}

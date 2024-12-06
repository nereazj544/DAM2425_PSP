package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.CMR.L;

import java.util.*;
import java.util.concurrent.locks.*;

public class ConL {
    private static Random r = new Random();

    static final int MAX_Prd = 10;
    static final int[] cadena = new int[MAX_Prd];

    static int tlEm = 0;
    static int posAc = 0;

    private static final Lock l = new ReentrantLock();
    private static final Condition hayEs = l.newCondition();
    private static final Condition hayPro = l.newCondition();

    static class RobotCl extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    l.lock();

                    while (posAc >= MAX_Prd) {
                        hayEs.await();
                    }

                    int t = r.nextInt(3) + 1;

                    cadena[posAc++] = t;

                    System.out.println("> Colocado prod tipo: " + t);

                    hayPro.signalAll();
                    sleep(r.nextInt(1000));

                } catch (Exception e) {
                    // TODO: handle exception
                    e.getMessage();
                }
            }
        }

    }

    static class RobotEm extends Thread {

        private int tipoes;

        public RobotEm(int t) {
            this.tipoes = t;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    l.lock();
                    boolean en = false;
                    while (!en) {
                        if (posAc == 0) {
                            hayPro.await();
                            continue;
                        }
                        for (int i = 0; i < posAc; i++) {
                            if (cadena[i] == tipoes) {
                                System.out.println("> Robot: " + tipoes + " empaquetado pro");

                                for (int j = 0; j < posAc - 1; j++) {
                                    cadena[j] = cadena[j + 1];
                                }

                                posAc--;
                                tlEm++;
                                en = true;

                                hayEs.signal();
                            }
                        }
                        if (!en) {
                            hayPro.await();
                        }
                    }
                    l.unlock();
                    sleep(r.nextInt(1000));
                        
                } catch (Exception e) {
                    // TODO: handle exception
                    e.getMessage();
                }
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

    // ! End
}

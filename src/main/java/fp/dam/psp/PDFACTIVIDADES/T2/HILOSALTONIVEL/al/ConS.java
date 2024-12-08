package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.al;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;



public class ConS extends Thread {
    protected int[] al;
    protected String n;
    protected Semaphore s;

    public ConS(int[] alOr, String n, Semaphore s) {
        this.al = Arrays.copyOf(alOr, alOr.length);
        this.n = n;
        this.s = s;
    }

    protected void mEstado() {
        try {
            s.acquire();
            System.out.printf("%s: %n", n, Arrays.toString(al));

            s.release();

            sleep(500);

        } catch (Exception e) {
            // TODO: handle exception
            interrupt();
        }
    }

    class OrdenIn extends ConS {

        public OrdenIn(int[] al, Semaphore s) {
            super(al, "Insercion", s);
        }

        @Override
        public void run() {
            for (int i = 0; i < al.length; i++) {
                int v = al[i];
                int j = i - 1;
                while (j >= 0 && al[i] > v) {
                    al[j + 1] = al[j];
                    j--;
                }
                al[j + 1] = v;
                mEstado();
            }
        }
    }

    class OrdenS extends ConS {
        public OrdenS(int[] al, Semaphore semaforoImpresion) {
            super(al, "Selección", semaforoImpresion);
        }

        @Override
        public void run() {
            for (int i = 0; i < al.length - 1; i++) {
                int minIdx = i;
                for (int j = i + 1; j < al.length; j++) {
                    if (al[j] < al[minIdx]) {
                        minIdx = j;
                    }
                }
                int temp = al[i];
                al[i] = al[minIdx];
                al[minIdx] = temp;
                mEstado();
            }
        }
    }

    class OrdenB extends ConS {
        public OrdenB(int[] al, Semaphore semaforoImpresion) {
            super(al, "Burbuja", semaforoImpresion);
        }

        @Override
        public void run() {
            for (int i = 0; i < al.length - 1; i++) {
                for (int j = 0; j < al.length - i - 1; j++) {
                    if (al[j] > al[j + 1]) {
                        int temp = al[j];
                        al[j] = al[j + 1];
                        al[j + 1] = temp;
                    }
                }
                mEstado();
            }
        }
    }

    public static void main(String[] args) {
        Random r = new Random();
        int[] alOr = r.ints(15,1,100).toArray();
        Semaphore s = new Semaphore(1);

        ConS c = new ConS(alOr, null, s);
        System.out.println("> Array Or: " + Arrays.toString(alOr));
        System.out.println("> Iniciando ordenacion....");
        Thread [] al = {
            c.new OrdenIn(alOr, s),
            c.new OrdenS(alOr, s),
            c.new OrdenB(alOr, s),
        };

        for (Thread thread : al) {
            thread.start();
        }

        for (Thread thread : al) {
            try {
                thread.join();
            } catch (Exception e) {
                // TODO: handle exception
                interrupted();
            }
        }
    }

}

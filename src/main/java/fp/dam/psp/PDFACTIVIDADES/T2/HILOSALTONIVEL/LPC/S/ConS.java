package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.LPC.S;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.Semaphore;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConS {
    static Semaphore s = new Semaphore(1);
    private static long tL = 0; // Lineas
    private static long tP = 0; // Palabras
    private static long tC = 0; // Caracteres

    static class Contador extends Thread {
        String file;

        public Contador(String file) {
            this.file = file;
        }

        @Override
        public void run() {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                long l = 0; // Linea
                long p = 0; // Palabras
                long c = 0; // Caracteres

                String ln;

                while ((ln = br.readLine()) != null) {
                    l++;
                    c += ln.length();
                    Matcher m = Pattern.compile("\\p{Alpha}+").matcher(ln);

                    while (m.find()) {
                        p++;
                    }
                }

                s.acquire();

                try {
                    tL += l;
                    tC += c;
                    tP += p;

                    System.out.printf("> %s: %d lineas, " + "%d palabras, %d caracteres %n", file, l, p, c);

                } finally {
                    s.release();
                }

            } catch (Exception e) {
                e.getMessage();
            }

        }

        public static void main(String[] args) {
            if (args.length < 2) {
                System.out.println("S/C +  Fichero");
                System.exit(0);
            }

            boolean s = args[0].equals("s");

            long i = System.currentTimeMillis();

            if (s) {
                for (int j = 0; j < args.length; j++) {
                    new Contador(args[j]).run();
                }
            } else {
                Thread[] h = new Thread[args.length - 1];
                for (int j = 0; j < h.length; j++) {
                    h[j - 1] = new Thread(new Contador(args[j]));
                    h[j - 1].start();
                }

                for (Thread thread : h) {
                    try {
                        thread.join();
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.getMessage();
                    }
                }
            }

            long end = System.currentTimeMillis();
            System.out.printf("%nTotal: %d lienas, %d palabras, %d caractere%n", tL, tP, tC);

            System.out.printf("TIempo: %d ms%n", end - i);

        }
    }
}

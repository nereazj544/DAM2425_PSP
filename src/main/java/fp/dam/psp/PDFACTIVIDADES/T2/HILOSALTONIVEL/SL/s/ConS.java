package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.SL.s;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class ConS {

    private static Semaphore[] s;
    private static int th;

    static class Escribir extends Thread {

        private static char letra;
        private static int rep;
        private static int mT;

        public Escribir(char letra, int rep, int mT) {
            this.letra = letra;
            this.rep = rep;
            this.mT = mT;
        }

        @Override
        public void run() {
            while (true) {
                try{
                    s[mT].acquire();

                    for (int i = 0; i < rep; i++) {
                        System.out.println(letra);
                        sleep(500);
                    }
                    int sig = (mT + 1) % th;
                    s[sig].release();

                }catch(Exception e){
                    e.getMessage();
                    interrupt();
                }
            }
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("> Patron: ");
        String patron = sc.nextLine().toUpperCase();

        if (!patron.matches("([A-Z]\\d+)+")) {
            System.out.println("INCORRECTO");
            System.exit(0);
        }

        Map<Character, Integer> lRep = new LinkedHashMap<>();

        for (int i = 0; i < patron.length(); i+=2) {
            char letra = patron.charAt(i);
            int num = Character.getNumericValue(patron.charAt(i+1));
            lRep.put(letra, num);
        }

        th = lRep.size();
        s = new Semaphore[th];
        for (int i = 0; i < th; i++) {
            if (i == 0) {
                s[i] = new Semaphore(1);
            }else{
                s[i] = new Semaphore(0);

            }
            
            int t = 0;
            for (Map.Entry<Character, Integer> en : lRep.entrySet()) {
                new Escribir(en.getKey(), en.getValue(), t++).start();;
            }
        }
    }

}

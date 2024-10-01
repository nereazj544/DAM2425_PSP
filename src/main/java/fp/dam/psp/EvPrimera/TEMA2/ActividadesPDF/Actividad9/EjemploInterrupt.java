package fp.dam.psp.EvPrimera.TEMA2.ActividadesPDF.Actividad9;

import java.util.Scanner;

public class EjemploInterrupt extends Thread {
    int c = 0;

    public void run() {
        while (!isInterrupted()) {
            // System.out.println("en el Hilo");
            c++; //incrementamos el contador
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("interrumpido mientras dormía");
                interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        EjemploInterrupt h = new EjemploInterrupt();
        h.start();
        System.out.println("fin para terminar");
        Scanner sc = new Scanner(System.in); //se esepera a que se introduzca fin
        String f = sc.nextLine();
        // if (!f.equals("fin")) {
        //     System.out.println("no has puesto fin/procedo a petarme");
        // }else{
            // Thread.sleep(2000);
            //se interrumpe el hilo
            if (f.equals("fin")) {
                
                h.interrupt();
                h.join();
                
                System.out.println("hilo finalizado. valos del contador: " + h.c);
            }
        // }
        sc.close();
    }
}
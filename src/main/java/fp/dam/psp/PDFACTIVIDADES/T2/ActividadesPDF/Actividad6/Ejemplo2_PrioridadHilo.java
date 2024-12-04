package fp.dam.psp.PDFACTIVIDADES.T2.ActividadesPDF.Actividad6;

import java.util.Scanner;

/**
 * Ejemplo2_PrioridadHilo
 */
public class Ejemplo2_PrioridadHilo implements Runnable {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            System.out.printf("%s(Prioridad: %d), mensaje %d\n", Thread.currentThread().getName(),
                    Thread.currentThread().getPriority(), i);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Runnable tarea = new Ejemplo2_PrioridadHilo();

        System.out.println(
                "> Si quieres que salga la minima proiridad pon los numeros en negativo o simplemente poniendo 1");
        System.out.println(
                "> Si quieres que salga la maxima proiridad pon los numeros en mayores a 10 o simplemente poniendo 10");
        System.out.println("> Si quieres que salga la proiridad de defecto pon el numero 5");
        for (int i = 1; i <= 3; i++) {
            System.out.println("> Prioridad del Hilo (1-10):");
            int p = sc.nextInt();

            if (p <= Thread.MIN_PRIORITY) {
                System.out.println("> La prioridad minima es 1");
                p = Thread.MIN_PRIORITY;
            } else if (p >= Thread.MAX_PRIORITY) {
                System.out.println("> La prioridad maxima es 10");
                p = Thread.MAX_PRIORITY;
            } else if (p == Thread.NORM_PRIORITY) {
                System.out.println("> La prioridad por defecto es 5");
                p = Thread.NORM_PRIORITY;
            }
            // if (p < Thread.MIN_PRIORITY || p > Thread.MAX_PRIORITY) {
            // if (p <= 1 || p <= Thread.MIN_PRIORITY) {
            // System.out.println("> La prioridad minima es 1");
            // p = Thread.MIN_PRIORITY;
            // } else if (p == 5 || p == Thread.NORM_PRIORITY) {
            // System.out.println("> La prioridad por defecto es 5");
            // p = Thread.NORM_PRIORITY;
            // } else if (p >= 10 || p >= Thread.MAX_PRIORITY) {
            // System.out.println("> La prioridad maxima es 10");
            // p = Thread.MAX_PRIORITY;

            Thread h = new Thread(tarea, "Hilo " + i);
            h.setPriority(p);
            h.start();
        }
        sc.close();
    }

}

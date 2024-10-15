package fp.dam.psp.EvPrimera.TEMA2.ActividadesPDF.Actividad10;

import java.util.Scanner;

public class ConScanner extends Thread{
    static boolean s = false;
    static boolean f = false;

    public synchronized void sus (){
        s = true;
    }

    public synchronized void r (){
        s = false;
        notify();
    }
    
    public synchronized void fin(){
        f = true;
        interrupt();
    }



    @Override
    public void run() {
        while (!f) {
            try {
                // Bloque sincronizado por un monitor
                // ! ⤵️ MONITOR
                synchronized (this) {
                    if (s) {
                        System.out.println("suspendido");
                        wait();
                        // El hilo ha quedado en espera hasta que reciba la notificacion
                    }
                }
                System.out.println("en ejecucion");
                Thread.sleep(1000);
                // Se pausa la ejecucion
            } catch (InterruptedException e) {
                if (f) {
                    System.out.println("Hilo interrumpido y finalizando");
                    break;
                }
            }
        }
    }




    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ConScanner h = new ConScanner();

        h.start();

        do {
            System.out.println("s -> supender / r -> renudar / f -> finalizar");
           String opcion = sc.nextLine();
            // Switch para depender de la opcion puesta: s se supende el hilo, r se renuada
            // (se notifica al hilo que puede seguir)
            switch (opcion.toLowerCase()) {
                case "s":
                    h.sus();
                    break;
                case "r":
                    h.r();
                    break;
                case "f":
                    h.fin();
                    break;

                default:
                    break;
            }
        } while (true);
        
    }

    
}

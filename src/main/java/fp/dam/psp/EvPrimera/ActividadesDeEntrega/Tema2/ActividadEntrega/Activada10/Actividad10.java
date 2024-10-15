package fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Activada10;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Actividad10 extends Thread {
    // Variable para controlar el estado de suspension del hilo
    static boolean suspendido = false;
    static boolean f = false; // Opcion de finalizar el hilo

    // Metodo sincronizado para suspender
    public synchronized void suspendido() {
        suspendido = true;
    }

    // Metodo sincronizado para reanudar
    public synchronized void reaundar() {
        suspendido = false;
        notify();
        // Notificamos al hilo para que dejen de estar en el estado de "bloqueo", una
        // vez que el "bloqueo" este libre
    }

    public synchronized void fin() {
        f = true;
        interrupt(); // Se finaliza el hilo
    }

    @Override
    public void run() {
        while (!f) {
            try {
                // Bloque sincronizado por un monitor
                // ! ⤵️ MONITOR
                synchronized (this) {
                    if (suspendido) {
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

    public static void main(String[] args) throws Exception {

        // Añadimos el BufferedReader para poder leer datos por teclado
        // ? No era mas facil poner un Scanner(?)

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        Actividad10 hilo = new Actividad10();
        hilo.start(); // Empieza el funcionamiento del hilo
        do {
            System.out.println("s -> supender / r -> renudar / f -> finalizar");
            String opcion = in.readLine();
            // Switch para depender de la opcion puesta: s se supende el hilo, r se renuada
            // (se notifica al hilo que puede seguir)
            switch (opcion.toLowerCase()) {
                case "s":
                    hilo.suspendido();
                    break;
                case "r":
                    hilo.reaundar();
                    break;
                case "f":
                    hilo.fin();
                    break;

                default:
                    break;
            }
        } while (true); // Bucle infinito.

    }

}

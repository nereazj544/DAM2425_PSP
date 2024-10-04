package fp.dam.psp.EvPrimera.TEMA2.ActividadesPDF.Actividad10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EjemploSuspension extends Thread {
    static boolean suspendido = false;

    public synchronized void suspender() {
        suspendido = true;
    }

    public synchronized void reanudar() {
        suspendido = false;
        notify();
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    if (suspendido) {
                        System.out.println("suspendido");
                        wait();
                    }
                }
                System.out.println("en ejecución");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        EjemploSuspension hilo = new EjemploSuspension();
        hilo.start();
        do {
            String opcion = in.readLine();
            System.out.println("s -> suspender / r -> reanudar");
            switch (opcion.toLowerCase()) {
                case "s":
                    hilo.suspender();
                    break;
                case "r":
                    hilo.reanudar();
                    break;
            }
        } while (true);
    }
}
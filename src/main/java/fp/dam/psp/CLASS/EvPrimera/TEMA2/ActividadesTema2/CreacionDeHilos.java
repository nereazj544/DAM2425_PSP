package fp.dam.psp.CLASS.EvPrimera.TEMA2.ActividadesTema2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CreacionDeHilos extends Thread {
    int id;
    int mimir;

    public CreacionDeHilos(int id, int mimir) {
        this.id = id;
        this.mimir = mimir;
    }

    @Override
    public void run() {
        System.out.println("> Hilo: " + id + " activado. \n> Dormira por: " + mimir);

        try {
            Thread.sleep(mimir);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("> Hilo: " + id + " finalizado.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("> Inserta el numero de hilos: ");

        int N = sc.nextInt();
        sc.close();

        List<Thread> h = new ArrayList<>();

        Random r = new Random();


        for (int i = 0; i < N; i++) {
            int mimir = r.nextInt(5000) + 1000;
            Thread hilo = new CreacionDeHilos(i, mimir); 
            h.add(hilo);
            hilo.start();
        }
        for (Thread thread : h) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("> Todos los hilos: " + N + " han finalizado");
    }
}

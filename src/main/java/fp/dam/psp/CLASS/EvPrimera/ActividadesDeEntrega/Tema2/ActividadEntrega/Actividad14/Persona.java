package fp.dam.psp.CLASS.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad14;

import java.util.Random;

public class Persona extends Thread {
    private static int c = 0;
    private int id;
    private Banco b;
    private Random r = new Random();

    public Persona(Banco b) {
        this.id = ++c;
        this.b = b;
    }

    @Override
    public void run() {
        try {

            while (true) {
                int tPaseo = r.nextInt(MAX_PRIORITY) + 1000;
                System.out.println("Persona " + id + " paseando");
                Thread.sleep(tPaseo);
                System.out.println("Persona " + id + " intenta sentarse");
                b.sentarse();
                System.out.println("Persona " + id + " se sienta");

                int tD = r.nextInt(MIN_PRIORITY) + 100;
                b.levantarse();
                System.out.println("Persona " + id + " se levanta");


            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
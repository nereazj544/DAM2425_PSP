package fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad15.Ejemplo14;

import java.util.concurrent.Semaphore;

public class Parque {
    private static int NUMPERSONAS = 20;
    private static int NUMPLAZAS = 5;
    public final static Semaphore BANCO = new Semaphore(NUMPLAZAS);

    public static void main(String[] args) throws InterruptedException {
        Thread[] personas = new Thread[NUMPERSONAS];
        for (int i = 1; i < NUMPERSONAS; i++)
            (personas[i] = new Persona(i + 1)).start();
        for (int i = 1; i < NUMPERSONAS; i++)
            personas[i].join();
    }

}

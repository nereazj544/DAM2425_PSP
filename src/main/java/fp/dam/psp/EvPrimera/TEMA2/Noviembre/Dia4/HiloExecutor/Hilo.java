package fp.dam.psp.EvPrimera.TEMA2.Noviembre.Dia4.HiloExecutor;

import fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema1.ActividadesEntrega.Actividad3.A.Ejecutar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Hilo implements  Runnable{
    long tiempo; //tiempo de mimir
    public Hilo(long tiempo){
        this.tiempo = tiempo;
    }


    public static void main(String[] args) {
        ExecutorService service =
                Executors.newFixedThreadPool(10); //Hilos maximos a crear
        for (int i = 0; i < 20; i++) {
            service.submit(new Hilo(1 * 500));
        }



    }

    @Override
    public void run() {
        //DEFINICION DE LA TAREA
        System.out.println(Thread.currentThread().getName() + " dormri " + tiempo);
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " fin ");
    }
}

package fp.dam.psp.CLASS.EvPrimera.ActividadesDeEntrega.Tema2.Noviembre.Dia4.HiloExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Hilo implements  Runnable{
    long tiempo; //tiempo de mimir
    public Hilo(long tiempo){
        this.tiempo = tiempo;
    }


    public static void main(String[] args) {
        //! Esto es lo que crea los hilos
        ExecutorService service =
                Executors.newFixedThreadPool(10); //Hilos maximos a crear

        for (int i = 0; i < 20; i++) {
            service.submit(new Hilo(i * 500));
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

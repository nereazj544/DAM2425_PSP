package fp.dam.psp.CLASS.EvPrimera.ActividadesDeEntrega.Tema2.Noviembre.Dia8;

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
        //? El newFixedThreadPool es lo más basico. El parentesis es para crear los hilos
    //? Dependiendo de lo que creemo se van gestionando solitos o no
//        for (int i = 0; i < 20; i++) {
//            service.submit(new Hilo(i * 500));
//        service.shutdown();

        }

    @Override
    public void run() {
        //DEFINICION DE LA TAREA
        System.out.println(Thread.currentThread().getName() + " dormri " + tiempo);
//        try {
//            Thread.sleep(tiempo);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(Thread.currentThread().getName() + " fin ");

        //! HILOS OBEDIENTES. CUANDO APARECE ALGO QUE ES _RED FLAG_ LA VANDERA SE ENCIENDE (SALIO EL TOXICO A ACTIVAR LA EXCEPCION),
        // !CUANDO ES _GREEN FLAG_ LA BANDEREA SE BAJA PORQUE NADIE TIRO DE LA EXCEPCION
        while (!Thread.currentThread().isInterrupted()){
            System.out.println("TAREA");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }
}


}

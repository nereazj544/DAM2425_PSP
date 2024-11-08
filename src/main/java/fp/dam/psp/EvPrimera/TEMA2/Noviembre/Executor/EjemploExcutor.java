package fp.dam.psp.EvPrimera.TEMA2.Noviembre.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EjemploExcutor {
    public static void main(String[] args) {
        
        //Se crea un pool de hilos fijo
        ExecutorService service = Executors.newFixedThreadPool(10); 

        //Ejecuta la tarea
        service.execute(() -> {
            System.out.println("Execute _ Tarea: " + Thread.currentThread().getId());
        });

        //Multiples tareas
        for (int i = 0; i < 10; i++) {
            int tarea = i;
            service.execute(() -> {
                System.out.println("Multiples tarea: " + tarea + " ejecutando por " + Thread.currentThread().getName());
            });
        }

        service.shutdownNow();
    }
}

package fp.dam.psp.CLASS.EvPrimera.ActividadesDeEntrega.Tema2.Noviembre.Dia8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Ejemplo {

    static class Contador {
        ReentrantLock lock = new ReentrantLock();
        private int n;

        public Contador(int n) {
            this.n = n;
        }

        public void inc() {
            lock.lock();
            try {
                n++;
            } finally {
                lock.unlock();
            }
        }

        public int get() {
            return n;
        }

        public static void main(String[] args) {
            Contador c = new Contador(100);
            // TODO: Crear varios hilos crementando el contador con ExecutorService
            ExecutorService service = Executors.newFixedThreadPool(5);
            for (int i = 0; i < 15; i++) {
                service.submit(() -> c.inc());
            }
            service.shutdownNow();

            try {
                service.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Valor final del contador: " + c.get());
//! clase
//            for (int i = 0; i < 5; i++) {
//                service.submit(Ejemplo::tarea);
//
//            }
//            service.shutdown();

        }



    }
}
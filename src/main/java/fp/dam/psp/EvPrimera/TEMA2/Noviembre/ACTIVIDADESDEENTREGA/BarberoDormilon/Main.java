package fp.dam.psp.EvPrimera.TEMA2.Noviembre.ACTIVIDADESDEENTREGA.BarberoDormilon;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        Barberia b = new Barberia(5);
        Barbero br = new Barbero(b);

        // ExecutorService s = Executors.newFixedThreadPool(3);
        ExecutorService s = Executors.newCachedThreadPool();


        s.execute(br);

        //! CLIENTES
        for (int i = 0; i < 15; i++) {
            s.execute(new Cliente(b));
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                // TODO: handle exception
                Thread.currentThread().interrupt();
            }
        }

        s.shutdown();
    }

}

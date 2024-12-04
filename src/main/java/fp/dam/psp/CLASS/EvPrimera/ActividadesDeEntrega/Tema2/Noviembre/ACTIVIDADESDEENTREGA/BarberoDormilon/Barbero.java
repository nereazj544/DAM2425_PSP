package fp.dam.psp.CLASS.EvPrimera.ActividadesDeEntrega.Tema2.Noviembre.ACTIVIDADESDEENTREGA.BarberoDormilon;

public class Barbero implements Runnable {
    Barberia b;
    Cliente c;

    public Barbero(Barberia b) {
        this.b = b;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("> Barbero_ DURMIENDO");
                b.esCnt();
                System.out.println("> Barbero_ Cortando al Cliente");
                Thread.sleep(2000);
                System.out.println("> Barbero_ Cortando Fin");
            }
        } catch (Exception e) {
            // TODO: handle exception
            Thread.currentThread().interrupt();
        }

    }
}

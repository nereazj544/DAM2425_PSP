package fp.dam.psp.CLASS.EvPrimera.ActividadesDeEntrega.Tema2.Noviembre.ACTIVIDADESDEENTREGA.BarberoDormilon;

public class Cliente implements Runnable {

    int id;
    private static int cClie = 0;
    Barberia b;

    public Cliente(Barberia b) {
        this.b = b;
        // this.id = cClie++;
        this.id = ++cClie;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        if (b.entrarEnSalaDeEspera()) {
            System.out.println("> Cliente _ id " + id + " esperando");
            b.solicitarCortePelo();
            System.out.println("> Cliente _ id " + id + " Cortandose");

        } else {
            System.out.println("> Cliente _ id " + id + " Hasta luego MariCarmen, mal servicio. Reseña negativa :P");
        }
    }

}

package fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad13;

public class Consumidor extends Thread {
    private long retardo;
    private Almacen almacen;

    public Consumidor(Almacen almacen, long retardo) {
        this.retardo = retardo;
        this.almacen = almacen;
    }

    public void run() {
        while (true) {
            String producto = almacen.retirar(); //Retira un produco
            System.out.println("producto " + producto + " retirado");
            try {
                Thread.sleep(retardo);
            } catch (InterruptedException e) {
            }
        }
    }
}

package fp.dam.psp.EvPrimera.TEMA2.OCTUBRE.Dia18.Provedores_Clase;

public class Consumidor extends  Thread{
    private long retardo;
    private Almacen almacen;
    public Consumidor(Almacen almacen, long retardo) {
        super("consumidor");
        this.retardo = retardo;
        this.almacen = almacen;
    }
    public void run(){
        while (true) {
            String producto = almacen.retirar();
            System.out.println("producto " + producto + " retirado");
            try { Thread.sleep(retardo); } catch (InterruptedException e) {}
        }
    }

}

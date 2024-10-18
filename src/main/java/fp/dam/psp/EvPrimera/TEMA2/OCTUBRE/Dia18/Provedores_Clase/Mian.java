package fp.dam.psp.EvPrimera.TEMA2.OCTUBRE.Dia18.Provedores_Clase;

public class Mian {
    public static void main(String[] args) {
        Almacen almacen = new Almacen(10);
        Prodcutor productor = new Prodcutor(almacen, 100);
        Consumidor consumidor = new Consumidor(almacen, 1000);
        productor.start();
        consumidor.start();
    }
}

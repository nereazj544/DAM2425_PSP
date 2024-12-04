package fp.dam.psp.CLASS.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad13;

public class Main {
    public static void main(String[] args) {

        Almacen almacen = new Almacen(10); // Se crea un almacen con una capacidad de 10 elementos
        // Productor productor = new Productor(almacen, 100);
        // Consumidor consumidor = new Consumidor(almacen, 1000);

        // * Iniciacion de los hilos
        // productor.start();
        // consumidor.start();
        for (int i = 0; i < 3; i++) {
            new Productor(almacen, 100).start();
            new Consumidor(almacen, 100).start();
        }

        

        System.out.println("> Hilos fin");
    }
}

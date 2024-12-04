package fp.dam.psp.CLASS.EvPrimera.TEMA2.OCTUBRE.Dia18.Provedores_Clase;

public class Almacen {
    private int almacenados = 0;
    private String [] productos;
    public Almacen(int capacidad) {
        productos = new String[capacidad];
    }

    //! Secciones criticas = Exclusion mutua.
    public synchronized void almacenar(String producto) {
        if (almacenados == productos.length) //? almacén lleno
            //! Si esta lleno no podra almacenar nada.
            try {
                wait();
            } catch (InterruptedException e) {}
        productos[almacenados++] = producto;
        notify();
    }

    public synchronized String retirar() {
        if (almacenados == 0) // almacén vacío
            try {
                wait();
            } catch (InterruptedException e) {}
        String producto = productos[--almacenados];
        notify();
        return producto;
    }
}

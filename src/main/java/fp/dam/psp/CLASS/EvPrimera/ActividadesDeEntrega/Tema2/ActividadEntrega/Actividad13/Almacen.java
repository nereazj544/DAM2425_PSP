package fp.dam.psp.CLASS.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad13;

public class Almacen {
    private int almacenados = 0; // Contador de productos
    private String[] productos; // Almacenar los productos

    public Almacen(int capacidad) {
        productos = new String[capacidad];
    }

    public synchronized void almacenar(String producto) {
        if (almacenados == productos.length) // almacén lleno
            try {
                wait(); 
                /*
                ! Sobre el estado Wait
                 * Cuando el hilo entra en este estado, libera el cerrojo del monitor, por lo
                 * que se quedara bloqueado y asi puede acceder otro hilo que necesite acceder a
                 * los bloques.
                ! Sobre los monitories:
                 * Cuando se usa el mismo monitor para todo: comparten el mismo cerrojo.
                 */
            } catch (InterruptedException e) {
            }
        productos[almacenados++] = producto; //Se añade producto
        //Se notifica al hilo que esta en espera.
        // notify(); 
        notifyAll();
    }
    
    public synchronized String retirar() {
        if (almacenados == 0) // almacén vacío
        try {
            wait();
        } catch (InterruptedException e) {
        }
        String producto = productos[--almacenados]; //Se retira producto
        // notify();
        notifyAll();
        return producto;
    }
}

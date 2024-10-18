package fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad14;

public class Banco {
    int plazasO = 0; // Ocupado
    int capacidad; // Capacidad

    public Banco(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized void sentarse() throws InterruptedException{
        while (plazasO >= capacidad) {
            wait();
        }
        plazasO++;
    }
    public synchronized void levantarse() {
        plazasO--;
        notify();
    }
}

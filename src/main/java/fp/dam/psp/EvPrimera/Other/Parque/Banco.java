package fp.dam.psp.EvPrimera.Other.Parque;

public class Banco {
    int plazasO = 0; // Ocupado
    int capacidad; // Capacidad

    public Banco(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized boolean sentarse() throws InterruptedException{
        while (plazasO >= capacidad) {
            plazasO++;
            return true;
        }
        return false;
        
    }
    public synchronized void levantarse() {
        plazasO--;
        notify();
    }
}

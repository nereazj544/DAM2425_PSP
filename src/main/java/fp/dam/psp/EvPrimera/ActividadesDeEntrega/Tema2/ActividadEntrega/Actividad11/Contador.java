package fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad11;

public class Contador {
    private int n;

    public Contador(int n) {
        this.n = n;
    }

    //El cambio con el ej 9 es el synchrinized
    public synchronized void inc() {
        n++;
    }

    public int get() {
        return n;
    }
}

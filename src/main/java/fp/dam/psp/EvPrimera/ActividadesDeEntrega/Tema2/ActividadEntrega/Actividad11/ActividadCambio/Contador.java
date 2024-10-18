package fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad11.ActividadCambio;

public class Contador {
    private int n;

    public Contador(int n){
        this.n = n;
    }


    public void inc(){
        synchronized(this){
            n++;
        }
    }
    public int get(){
        return n;
    }
}

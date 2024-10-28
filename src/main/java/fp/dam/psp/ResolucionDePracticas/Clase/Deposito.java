package fp.dam.psp.ResolucionDePracticas.Clase;

import fp.dam.psp.ResolucionDePracticas.GitJulio.Consola;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Deposito extends  HiloInterrumpible{
    ArrayList<Globo> des = new ArrayList<>();
    ArrayList<Globo> hin = new ArrayList<>();

    int max;
    int maxHin;
    Consola consola;
    int id;

    public Deposito(int max, int maxHin, Consola consola){
    this.max = max;
    this.maxHin = maxHin;
    }

    public synchronized void reponer() {
        while (des.size() + hin.size() == max){
            try {
                wait();
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
                //! El hilo que ejecute esto, puede ser interrumplibe
                Thread.currentThread().interrupt();
            }
        }
        Globo g = new Globo(++id, 5, consola);
        des.add(g);
        notifyAll();
    }

    public synchronized Globo getDes() {
        while (des.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Globo g = des.removeFirst();
        //* sE Movera el primero.
        hin.add(g);
        notifyAll();
        return  g;
    }

    public synchronized Globo getHin() {
        while (des.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
//        Globo g = des.removeFirst();
//        get(r.nextInt(hin.size()))
        //Hinchazo no des, y se se pasa un objeto random
        //* sE Movera el primero.
        notifyAll();
        return  g;
    }

    //! FALTA EL DE REMOVER GLOBO
    @Override
    protected void tarea() {
        //? Aqui se invoca a reponer

    }
}

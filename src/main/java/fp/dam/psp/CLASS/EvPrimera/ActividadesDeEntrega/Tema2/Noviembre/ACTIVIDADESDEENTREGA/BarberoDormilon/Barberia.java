package fp.dam.psp.CLASS.EvPrimera.ActividadesDeEntrega.Tema2.Noviembre.ACTIVIDADESDEENTREGA.BarberoDormilon;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Barberia {
    Semaphore salaEspera;
    Sillon sillon;

    ReentrantLock lock = new ReentrantLock();

    public Barberia(int sillas) {
        salaEspera = new Semaphore(sillas);
        sillon = new Sillon();
    }

    public boolean entrarEnSalaDeEspera() {
        if (salaEspera.tryAcquire())
            return false;
        else
            return true;
    }

    public void solicitarCortePelo() {
        lock.lock();
        try {
            sillon.ocp();
            salaEspera.release();
        } finally {
            lock.unlock();
            
        }
    }

    public void finalCP() {
        sillon.des();
    }

    public void esCnt() throws InterruptedException{
        sillon.esCli();
    }

    public boolean sillonOc(){
        return sillon.estaOc();
    }

    // ! END CLASS
}

package fp.dam.psp.EvPrimera.TEMA2.Noviembre.ACTIVIDADESDEENTREGA.BarberoDormilon;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Sillon {

    boolean oc = false;
    ReentrantLock lock = new ReentrantLock();
    Condition c = lock.newCondition();

    public void ocp() {
        // TODO Auto-generated method stub
        lock.lock();
        try {
            oc = true;
            // c.signal();
            c.signalAll();
        } finally {
            // TODO: handle exception
            lock.unlock();
        }
    }

    public void des() {
        // TODO Auto-generated method stub
        lock.lock();
        try {
            oc = false;

        } finally {
            // TODO: handle exception
            lock.unlock();
        }

    }

    public void esCli() throws InterruptedException {
        lock.lock();
        try {
            while (!oc) {
                c.await();
            }
        } finally {
            // TODO: handle exception
        }
    }

    public boolean estaOc() {
        lock.lock();
        try {
            return oc;
        } finally {
            lock.unlock();
        }

    }

    // ! EDN CLASS
}

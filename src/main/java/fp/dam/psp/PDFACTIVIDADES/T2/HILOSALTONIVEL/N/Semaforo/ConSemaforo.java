package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.N.Semaforo;

import java.util.concurrent.Semaphore;

public class ConSemaforo extends Thread {

    final int N = 5;

    Semaphore s = new Semaphore(1);

    int id;
    int mimir;

    public ConSemaforo(int id, int mimir) {
        this.id = id;
        this.mimir = mimir;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
    }
}

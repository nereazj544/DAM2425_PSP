package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.TT.Semafoto;

import java.util.concurrent.Semaphore;

public class ConSe extends Thread{
      Semaphore s = new Semaphore(1);
    String sonido;

    public ConSe(String sonido) {
        this.sonido = sonido;
    }

    @Override
    public void run() {
        while (true) {
            try {
                
                s.acquire();
                System.out.println(sonido);
                s.release();
                Thread.sleep(1000);



            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    public static void main(String[] args) {
        Thread tic = new Thread(new ConSe("TIC"));
        Thread tac = new Thread(new ConSe("TAC"));
        tic.start();
        tac.start();
    }
}

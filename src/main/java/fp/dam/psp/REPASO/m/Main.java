package fp.dam.psp.REPASO.m;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MiClase miClase = new MiClase();
        Thread t = new Hilo(miClase);
        t.start();
        Thread.sleep(5000);

        synchronized(miClase){miClase.notifyAll();}
    }
}



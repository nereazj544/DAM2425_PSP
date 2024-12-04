package fp.dam.psp.Other.Chinos;


public class Palillos extends Thread{
    boolean EnUso = false;
    public synchronized void Tomar() throws InterruptedException{
        while (EnUso) {
            wait();
        }
        EnUso = true;
    }

    public synchronized void Soltar(){
        EnUso = false;
        notify();
    }
}

package fp.dam.psp.REPASO.m;

public class Hilo extends Thread {
    MiClase miClase;

    public Hilo(MiClase miClase) {
        this.miClase = miClase;
    }

    @Override
    public void run(){
        miClase.unMetodo();
    }
}

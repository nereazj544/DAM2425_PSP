package fp.dam.psp.REPASO.m;

public class MiClase {

    public synchronized void unMetodo(){
        try {
            wait();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}

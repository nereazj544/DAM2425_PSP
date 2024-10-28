package fp.dam.psp.ResolucionDePracticas.Clase;

public abstract class HiloInterrumpible extends  Thread{
    boolean s = false;

    public synchronized  void p(){
        s = !s;
    }


    @Override
    public void run() {
        //Mientras no sea interumopido
        while (!interrupted()){
        synchronized (this) {
            while (s){
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        tarea();
        }
    }

    protected abstract void tarea();
}

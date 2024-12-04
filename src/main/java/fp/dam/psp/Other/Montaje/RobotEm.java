package fp.dam.psp.Other.Montaje;

public class RobotEm extends Thread {
    Cadena cadena;
    int tipoEs;
    static boolean s = false; // Suspendido
    static boolean f = false; // matar hilo

    public RobotEm(Cadena cadena, int tipoEs) {
        this.cadena = cadena;
        this.tipoEs = tipoEs;
    }

    // ! ======= METODOS PARA FINALIZAR LOS HILOS =====
    public synchronized void suspender() {
        s = true;
    }

    public synchronized void reanudar() {
        s = false;
        notifyAll();
    }

    public synchronized void fin() {
        f = true;
        interrupt();
    }

    @Override
    public void run() {
        try {
            while (!f) {
                synchronized(this){
                    while (s) {
                        wait();
                    }
                }

                while (true) {
                    cadena.retirar(tipoEs);
                    sleep(500);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}

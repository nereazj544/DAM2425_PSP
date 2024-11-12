package fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad16;


import static fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad16.Mesa.actualizar;

public class Filosofos extends Thread{
    String nombre;
    Palillos Piz;
    Palillos Pder;

    public Filosofos(String nombre, Palillos piz, Palillos pder) {
        this.nombre = nombre;
        Piz = piz;
        Pder = pder;
    }

    // ! ==== PARA ASESINAR Y PAUSAR LOS HILOS ===
    boolean s = false;
    boolean f = false;

    // ! ======= METODOS PARA FINALIZAR LOS HILOS =====
    public synchronized void suspender() {
        s = true;
    }

    public synchronized void reanudar() {
        s = false;
        // notifyAll();
        notify();
    }

    public synchronized void fin() {
        f = true;
        interrupt();
    }

    private void pensar() throws InterruptedException {
        actualizar(nombre + " esta pensando\n");
        sleep(1000);
    }

    private void comer() throws InterruptedException {
        actualizar(nombre + " esta comiendo\n");
        sleep(1000);
    }

    @Override
    public void run() {
        try {
            while (!f) {
                synchronized (this) {
                    while (s) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                pensar();
                Piz.Tomar();

                try {
                    Pder.Tomar();
                    try {
                        comer();
                    } finally {
                        Pder.Soltar();
                    }
                } finally {
                    Piz.Soltar();

                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


}

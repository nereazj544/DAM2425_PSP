package fp.dam.psp.EvPrimera.Other.Chinos;

import static fp.dam.psp.EvPrimera.Other.Chinos.Main.actualizar;

public class Filosofos extends Thread {
    String nombre;
    Palillos Piz;
    Palillos Pder;

    // ! ==== PARA ASESINAR Y PAUSAR LOS HILOS ===
    boolean s = false;
    boolean f = false;

    public Filosofos(String nombre, Palillos izquierdo, Palillos derecha) {
        this.nombre = nombre;
        Piz = izquierdo;
        Pder = derecha;
    }

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

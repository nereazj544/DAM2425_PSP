package fp.dam.psp.EvPrimera.Other.Ascensores.other;

public class ascensor extends Thread {
    private String nombre;
    private int pisoActual;
    private boolean suspendido = false;
    private boolean fin = false;

    public ascensor(String nombre, int pisoInicial) {
        this.nombre = nombre;
        this.pisoActual = pisoInicial;
    }

    public synchronized void suspender() {
        suspendido = true;
    }

    public synchronized void reanudar() {
        suspendido = false;
        notify();
    }

    public synchronized void terminar() {
        fin = true;
        interrupt();
    }

    @Override
    public void run() {
        while (!fin) {
            synchronized (this) {
                while (suspendido) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            // Simula movimiento del ascensor
            moverAscensor();
            try {
                sleep(1000);  // Simula tiempo de espera entre pisos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void moverAscensor() {
        // Simula movimiento del ascensor (puedes cambiar esta lógica según tus necesidades)
        System.out.println(nombre + " se mueve del piso " + pisoActual + " al piso " + (pisoActual + 1));
        pisoActual++;
        if (pisoActual > 20) {
            pisoActual = 0; // Si llega al último piso, vuelve a la planta baja
        }
    }
}

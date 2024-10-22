package fp.dam.psp.EvPrimera.Other.Piscina;

import java.util.Random;

public class Usuario extends Thread {
    private Piscina piscina;
    String nombre;
    boolean esSubarinista;
    String tipo;
    static boolean s = false; // Suspendido
    static boolean f = false; // matar hilo

    public Usuario(Piscina piscina, String nombre, boolean esSubarinista) {
        this.piscina = piscina;
        this.nombre = nombre;
        this.tipo = esSubarinista ? "Submarinista" : obtenerTipoNador();
    }

    private String obtenerTipoNador() {
        String[] tipos = { "Hombre", "Mujer", "Niño", "Niña" };
        return tipos[new Random().nextInt(tipos.length)];
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
        while (!f) {
            synchronized (this) {
                while (s) { // Revisión de suspensión
                    try {
                        wait(); // El hilo se suspende
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            
            // Interacción con la piscina
            if (esSubarinista) {
                piscina.EntrarSubmarinistas(nombre);
            } else {
                piscina.entrar(nombre, tipo);
            }
    
            try {
                // Simulación de tiempo dentro de la piscina
                sleep(new Random().nextInt(31) + 50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            // Salida de la piscina
            if (esSubarinista) {
                piscina.salirSub(nombre);
            } else {
                piscina.salirNadador(nombre, tipo);
            }
    
            try {
                // Espera antes de volver a intentar entrar
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}

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
       String [] tipos = {"Hombre", "Mujer", "Niño", "Niña"};
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

}

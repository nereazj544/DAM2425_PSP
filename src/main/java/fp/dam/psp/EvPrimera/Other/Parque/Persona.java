package fp.dam.psp.EvPrimera.Other.Parque;

import static fp.dam.psp.EvPrimera.Other.Parque.Main.actualizar;
import java.util.*;

public class Persona extends Thread {
    int id;
    Banco banco;
    static boolean s = false; // Suspendido
    static boolean f = false; // matar hilo
    Random r = new Random();

    public Persona(int id, Banco banco) {
        this.id = id;
        this.banco = banco;
    }

    // ! ======= METODOS PARA FINALIZAR LOS HILOS =====
    public synchronized void suspender() {
        s = true;
    }

    public synchronized void reanudar() {
        s = false;
        notify();
    }

    public synchronized void fin() {
        f = true;
        interrupt();
    }

    @Override
    public void run() {
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

            try {
                paser();
                intentarSentarse();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                interrupt();
            }
        }
    }

    private void paser() throws InterruptedException {
        actualizar("Persona " + id + " paseando");
        sleep(r.nextInt(2001) + 1000);
    }

    private void intentarSentarse() throws InterruptedException {
        actualizar("Persona " + id + " intenta sentarse en el banco\n");
        while (!banco.sentarse()) {
            actualizar("Persona " + id + " espera una plaza libre.\n");
            Thread.sleep(100);
        }
        actualizar("Persona " + id + " se sienta en el banco.\n");
        Thread.sleep(r.nextInt(601) + 100);
        banco.levantarse();
        actualizar("Persona " + id + " se levanta del banco.\n");
    }
    }


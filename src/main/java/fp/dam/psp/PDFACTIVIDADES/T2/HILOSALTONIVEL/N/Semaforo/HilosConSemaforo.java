package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.N.Semaforo;

import java.util.concurrent.Semaphore;

public class HilosConSemaforo {
    private static final int N = 3;
    private static Semaphore semaforo = new Semaphore(1);
    private static int hilosCompletados = 0;
    
    static class Trabajador implements Runnable {
        private int id;
        private int tiempoDormir;
        
        public Trabajador(int id, int tiempo) {
            this.id = id;
            this.tiempoDormir = tiempo;
        }
        
        public void run() {
            try {
                semaforo.acquire();
                System.out.println("Hilo " + id + 
                    " iniciando. Dormirá " + 
                    tiempoDormir + "ms");
                semaforo.release();
                
                Thread.sleep(tiempoDormir);
                
                semaforo.acquire();
                System.out.println("Hilo " + id + 
                    " finalizado");
                hilosCompletados++;
                
                if (hilosCompletados == N) {
                    System.out.println("Todos los hilos " +
                        "han terminado");
                }
                semaforo.release();
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        Thread[] hilos = new Thread[N];
        for (int i = 0; i < N; i++) {
            hilos[i] = new Thread(
                new Trabajador(i, (i+1)*1000));
            hilos[i].start();
        }
    }
}
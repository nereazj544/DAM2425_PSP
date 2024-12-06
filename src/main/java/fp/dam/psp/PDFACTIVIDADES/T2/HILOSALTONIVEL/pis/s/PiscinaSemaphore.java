package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.pis.s;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class PiscinaSemaphore {
    private static final int CALLES_TOTALES = 8;
    private static final int TOTAL_USUARIOS = 20;
    private static int callesOcupadas = 0;
    private static int hombres = 0, mujeres = 0;
    private static int ninos = 0, ninas = 0;
    private static int submarinistas = 0;
    private static final Semaphore calles = new Semaphore(CALLES_TOTALES);
    private static final Semaphore mutex = new Semaphore(1);
    private static final Random random = new Random();

    static class Usuario implements Runnable {
        private final String nombre;
        private final String tipo;
        private final int callesNecesarias;

        public Usuario(String nombre, String tipo) {
            this.nombre = nombre;
            this.tipo = tipo;
            this.callesNecesarias = tipo.equals("submarinista") ? 2 : 1;
        }

        private void nadar() throws InterruptedException {
            Thread.sleep(50 + random.nextInt(31));
        }

        private void actualizarContadores(boolean entrando) {
            int factor = entrando ? 1 : -1;
            switch(tipo) {
                case "hombre": hombres += factor; break;
                case "mujer": mujeres += factor; break;
                case "niño": ninos += factor; break;
                case "niña": ninas += factor; break;
                case "submarinista": submarinistas += factor; break;
            }
        }

        @Override
        public void run() {
            try {
                calles.acquire(callesNecesarias);
                mutex.acquire();
                
                callesOcupadas += callesNecesarias;
                actualizarContadores(true);
                System.out.printf("%s entra - H:%d M:%d N:%d Ñ:%d S:%d%n",
                    nombre, hombres, mujeres, ninos, ninas, submarinistas);
                mutex.release();

                nadar();

                mutex.acquire();
                callesOcupadas -= callesNecesarias;
                actualizarContadores(false);
                System.out.printf("%s sale - H:%d M:%d N:%d Ñ:%d S:%d%n",
                    nombre, hombres, mujeres, ninos, ninas, submarinistas);
                mutex.release();
                
                calles.release(callesNecesarias);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        String[] tipos = {"hombre", "mujer", "niño", "niña", "submarinista"};
        Thread[] usuarios = new Thread[TOTAL_USUARIOS];
        
        for (int i = 0; i < TOTAL_USUARIOS; i++) {
            String tipo = tipos[random.nextInt(tipos.length)];
            usuarios[i] = new Thread(new Usuario("Usuario" + (i+1), tipo));
            usuarios[i].start();
        }
    }
}
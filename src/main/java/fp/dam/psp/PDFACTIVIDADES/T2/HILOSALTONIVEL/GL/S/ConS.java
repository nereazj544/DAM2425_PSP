package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.GL.S;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ConS {

    // Clase para manejar el almacén de globos
    static class AlmacenGn {
        private static final int MAX_GLOBOS = 10; // Máximo número de globos
        private static final int MAX_HINCHANDO = 3; // Máximo de globos que pueden hincharse simultáneamente

        private final Semaphore semaforoGlobos = new Semaphore(MAX_GLOBOS, true);
        private final Semaphore semaforoHinchando = new Semaphore(MAX_HINCHANDO, true);
        private final Semaphore mutexGlobos = new Semaphore(1, true);

        private int siguienteGlobo = 1; // Contador de globos
        private final boolean[] globosHinchando = new boolean[MAX_GLOBOS]; // Estado de los globos

        // Método para obtener un globo
        public int obtenerGlobo(String hinchador) throws InterruptedException {
            semaforoGlobos.acquire();
            mutexGlobos.acquire();
            try {
                int numeroGlobo = siguienteGlobo++;
                System.out.printf("GLOBO %d ENTREGADO A %s%n", numeroGlobo, hinchador);
                return numeroGlobo;
            } finally {
                mutexGlobos.release();
            }
        }

        // Método para empezar a hinchar un globo
        public void empezarHinchar(int numeroGlobo) throws InterruptedException {
            semaforoHinchando.acquire();
            mutexGlobos.acquire();
            try {
                globosHinchando[numeroGlobo - 1] = true;
            } finally {
                mutexGlobos.release();
            }
        }

        // Método para finalizar un globo (ya sea que explote o se pinche)
        public void finalizarGlobo(int numeroGlobo) {
            try {
                mutexGlobos.acquire();
                globosHinchando[numeroGlobo - 1] = false;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                mutexGlobos.release();
                semaforoHinchando.release();
                semaforoGlobos.release();
            }
        }

        // Método para obtener un globo disponible para pinchar
        public Integer obtenerGloboParaPinchar() throws InterruptedException {
            mutexGlobos.acquire();
            try {
                for (int i = 0; i < MAX_GLOBOS; i++) {
                    if (globosHinchando[i]) {
                        return i + 1;
                    }
                }
                return null;
            } finally {
                mutexGlobos.release();
            }
        }
    }

    // Clase para los hinchadores de globos
    static class HinchaGlobos extends Thread {
        private final AlmacenGn almacen;
        private final String nombre;
        private static final int MAX_VOLUMEN = 5;

        public HinchaGlobos(AlmacenGn almacen, int id) {
            this.almacen = almacen;
            this.nombre = "HG" + id;
        }

        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    int numeroGlobo = almacen.obtenerGlobo(nombre);
                    almacen.empezarHinchar(numeroGlobo);

                    int volumen = 0;
                    while (volumen < MAX_VOLUMEN) {
                        Thread.sleep(1000);
                        volumen++;
                        System.out.printf("GLOBO %d VOLUMEN %d%n", numeroGlobo, volumen);
                    }

                    System.out.printf("GLOBO %d ESTALLA%n", numeroGlobo);
                    almacen.finalizarGlobo(numeroGlobo);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Clase para los pinchadores de globos
    static class PinchaGlobos extends Thread {
        private final AlmacenGn almacen;
        private final String nombre;
        private final Random random = new Random();

        public PinchaGlobos(AlmacenGn almacen, int id) {
            this.almacen = almacen;
            this.nombre = "PG" + id;
        }

        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    Thread.sleep(random.nextInt(9000) + 1000);
                    Integer globoAPinchar = almacen.obtenerGloboParaPinchar();

                    if (globoAPinchar != null) {
                        System.out.printf("GLOBO %d PINCHADO POR %s%n", globoAPinchar, nombre);
                        almacen.finalizarGlobo(globoAPinchar);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Clase principal
    public static void main(String[] args) {
        AlmacenGn almacen = new AlmacenGn();

        // Crear y arrancar hilos
        for (int i = 1; i <= 5; i++) {
            new HinchaGlobos(almacen, i).start();
            new PinchaGlobos(almacen, i).start();
        }
    }
}

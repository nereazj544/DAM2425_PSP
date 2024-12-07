package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.ASC;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class cs {

    class Ascensor {
        static final int MAX_CP = 8;
        static final int MAX_PS = 21;
        private final Semaphore cp = new Semaphore(MAX_CP, true);
        private final Semaphore smp = new Semaphore(1);

        private int pACS = 0; // Piso actual
        private String estado = "P"; // Estado: P (Parado), S (Subiendo), B (Bajando)
        private final int id;

        public Ascensor(int id) {
            this.id = id;
        }

        public int getPAc() throws InterruptedException {
            smp.acquire();
            try {
                return pACS;
            } finally {
                smp.release();
            }
        }

        public void setPisoActual(int piso) throws InterruptedException {
            smp.acquire();
            try {
                pACS = piso;
            } finally {
                smp.release();
            }
        }

        public void subir(Persona persona) throws InterruptedException {
            cp.acquire();
            System.out.printf("> Ascensor %d: %s sube en piso %d%n", id, persona.getId(), getPAc());
        }

        public void bajar(Persona persona) throws InterruptedException {
            cp.release();
            System.out.printf("> Ascensor %d: %s baja en piso %d%n", id, persona.getId(), getPAc());
        }

        public void moverA(int piso) throws InterruptedException {
            int pIn = getPAc();
            estado = (pIn < piso) ? "S" : "B"; // Determinar dirección
            Thread.sleep(Math.abs(piso - pIn) * 1000); // Simular tiempo de movimiento
            setPisoActual(piso);
            estado = "P"; // Detenerse al llegar
        }
    }

    class Persona extends Thread {
        private String id;
        private int pisoDestino;
        private int pisoInicial;
        private final Ascensor[] ascensores;
        private static final Random r = new Random();

        public Persona(int n, Ascensor[] ascensores) {
            this.id = "P" + n;
            this.ascensores = ascensores;
            this.pisoInicial = r.nextInt(Ascensor.MAX_PS);
            do {
                this.pisoDestino = r.nextInt(Ascensor.MAX_PS);
            } while (pisoDestino == pisoInicial);
        }

        public String getPersonaId() {
            return id;
        }

        @Override
        public void run() {
            try {
                System.out.printf("> %s en piso %d quiere ir al piso %d%n", id, pisoInicial, pisoDestino);

                // Seleccionar el ascensor más cercano
                Ascensor ascensor = ascensores[0];
                int menorDistancia = Math.abs(ascensor.getPAc() - pisoInicial);

                for (Ascensor a : ascensores) {
                    int distancia = Math.abs(a.getPAc() - pisoInicial);
                    if (distancia < menorDistancia) {
                        ascensor = a;
                        menorDistancia = distancia;
                    }
                }

                // Usar el ascensor seleccionado
                ascensor.moverA(pisoInicial);
                ascensor.subir(this);
                ascensor.moverA(pisoDestino);
                ascensor.bajar(this);

            } catch (Exception e) {
                interrupt();
            }
        }
    }

    public static void main(String[] args) {
        final int NM_As = 4; // Número de ascensores
        final int NM_PR = 50; // Número de personas
        Random r = new Random();
    
        // Instancia de la clase contenedora ConS
        cs outer = new cs();
    
        // Crear los ascensores
        Ascensor[] ascensores = new Ascensor[NM_As];
        for (int i = 0; i < NM_As; i++) {
            ascensores[i] = outer.new Ascensor(i + 1); // Clase interna
        }
    
        // Crear el pool de hilos
        ExecutorService es = Executors.newCachedThreadPool();
    
        try {
            for (int i = 0; i < NM_PR; i++) {
                Thread.sleep(r.nextInt(2000) + 500);
                es.execute(outer.new Persona(i + 1, ascensores)); // Clase interna
            }
            es.shutdown();
            es.awaitTermination(5, TimeUnit.MINUTES);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            es.shutdownNow();
        }
    }
    
}

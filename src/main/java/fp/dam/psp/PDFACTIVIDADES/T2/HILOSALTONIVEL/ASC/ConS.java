package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.ASC;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ConS {

    class Ascensor {
        static final int MAX_CP = 8;
        static final int MAX_PS = 21;
        private final Semaphore cp = new Semaphore(MAX_CP, true);
        private final Semaphore smp = new Semaphore(2);

        private int pACS = 0;

        private String estado = "P";
        private final int id;

        public Ascensor(int id) {
            this.id = id;
        }

        public int getPAc() throws InterruptedException {
            smp.acquire();
            int ps = pACS;
            smp.release();
            return ps;
        }

        public void setPisoActual(int piso) throws InterruptedException {
            smp.acquire();
            pACS = piso;
            smp.release();
        }

        public void subir(Persona persona) throws InterruptedException {
            cp.acquire();
            System.out.printf("> Ascensor %d: %s sube en piso %d%n", id, persona.getId(), getPAc());
        }

        public void bajar(Persona persona) throws InterruptedException {
            cp.release();
            System.out.printf("> Ascensor %d: %s sube en piso %d%n", id, persona.getId(), getPAc());
        }

        public void moverA(int piso) throws InterruptedException {
            int pIn = getPAc();

            // estado = pIn < piso ? "S" : "B";
            if (pIn < piso) {
                estado = "S";
            } else {
                estado = "B";
            }

            Thread.sleep(Math.abs(piso - pIn) * 1000);
            setPisoActual(piso);
            estado = "P";
        }

    }

    class Persona extends Thread {

        private  String id;
        private int pisoDestino;
        private int pisoInicial;
        private Ascensor[] ascensores;
        private static final Random r = new Random();

        public Persona(int n, Ascensor[] ascensores) {
            this.id = "P" + n;
            this.ascensores = ascensores;
            this.pisoInicial = r.nextInt(21);
            do {
                this.pisoDestino = r.nextInt(21);
            } while (pisoDestino == pisoInicial);
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                System.out.printf("> %s en piso %d quiere ir al piso %d%n", id, pisoInicial, pisoDestino);

                Ascensor ascensor = ascensores[0];

                ascensor.moverA(pisoInicial);
                ascensor.subir(this);
                
                ascensor.moverA(pisoDestino);
                ascensor.bajar(this);



            } catch (Exception e) {
                // TODO: handle exception
                interrupt();
            }
        }
        
        // ! end Persona
    }



    public static void main(String[] args) {
        int NM_As = 4;
        int NM_PR = 50;
        Random r = new Random();

        Ascensor [] as = new Ascensor[NM_As];

        ConS cs = new ConS(); //clase contenedora (sin esto no carrula nada)
        for (int i = 0; i < NM_As; i++) {
            as[i] = cs.new Ascensor(i+1);
        }

        ExecutorService es = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < NM_PR; i++) {
                Thread.sleep(r.nextInt(2000) + 500);
                es.execute(cs.new Persona(i, as));
            }
            es.shutdown();
            es.awaitTermination(5, TimeUnit.MINUTES);
        } catch (Exception e) {
            // TODO: handle exception
            Thread.interrupted();
        }finally{
            es.shutdownNow();
        }
    }
}

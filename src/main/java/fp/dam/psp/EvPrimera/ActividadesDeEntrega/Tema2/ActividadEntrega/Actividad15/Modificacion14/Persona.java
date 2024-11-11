package fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad15.Modificacion14;

public class Persona extends Thread {
    public Persona(int id) {
        super("Persona " + id);
    }

    // ! MODIFICIACION
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(getName() + " pasea por el parque");
                sleep(rTime(100, 3000));
                System.out.println(getName() + " llega al banco");
                
                while (!Parque.BANCO.tryAcquire()) {
                    System.out.println("Banco lleno, " + getName() + " vuelve a pasear");
                    sleep(rTime(500, 1500));
                }
                try{
                    System.out.println(getName() + " se ha sentado");
                    sleep(rTime(100, 600));

                }finally{
                    Parque.BANCO.release();
                    System.out.println(getName() + " se levanto");
                }
            }
        } catch (Exception e) {
            System.out.println(getName() + " se peto");
        }
    }

    private long rTime(int min, int max) {
        return Parque.r.nextInt(max - min + 1) + min;
    }

    // ! EJEMPLO
    // @Override
    // public void run() {
    // try {
    // System.out.println(getName() + " pasea por el parque");
    // Thread.sleep((long) (Math.random() * 2000 + 1000)); // tiempo paseando
    // System.out.println(getName() + " llega al banco");
    // if (Parque.BANCO.availablePermits() == 0)
    // System.out.println("banco lleno, " + getName() + " espera");
    // Parque.BANCO.acquire();
    // System.out.println(getName() + " se ha sentado");
    // Thread.sleep((long) (Math.random() * 500)); // tiempo sentada
    // Parque.BANCO.release();
    // System.out.println(getName() + " se ha levantado");
    // } catch (InterruptedException e) {
    // }
    // }

}

package fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad14;

public class Persona extends Thread {
    public Persona(int id) {
        super("Persona " + id);
    }

    @Override
    public void run() {
        try {
            System.out.println(getName() + " pasea por el parque");
            Thread.sleep((long) (Math.random() * 2000 + 1000)); // tiempo paseando
            System.out.println(getName() + " llega al banco");
            if (Actividad14.BANCO.availablePermits() == 0)
                System.out.println("banco lleno, " + getName() + " espera");
            Actividad14.BANCO.acquire();
            System.out.println(getName() + " se ha sentado");
            Thread.sleep((long) (Math.random() * 500)); // tiempo sentada
            Actividad14.BANCO.release();
            System.out.println(getName() + " se ha levantado");
        } catch (InterruptedException e) {}
    }
}

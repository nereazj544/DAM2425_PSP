package fp.dam.psp.CLASS.EvPrimera.TEMA2.Septiembre.Dia20;

public class Tarea implements  Runnable{
    private String nombre;

    public Tarea(String nombre) {
        this.nombre = nombre;
    }



//    Este hay que emplementarlo por cojones nada más se haga un Runnable
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            System.out.println(nombre + ", mensaje " + i);
        }
    }

    public static void main(String[] args) {
        Thread hilo1 = new Thread(new Tarea("Hilo 1"));
        Thread hilo2 = new Thread(new Tarea("Hilo 2"));
        Thread hilo3 = new Thread(new Tarea("Hilo 3"));

        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}

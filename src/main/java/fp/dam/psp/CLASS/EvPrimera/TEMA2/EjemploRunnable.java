package fp.dam.psp.CLASS.EvPrimera.TEMA2;

public class EjemploRunnable implements  Runnable{
    // La clase implementa la interfaz Runnable
    private  String name;

    // El constructor recibe el nombre para identificar el hilo
    private EjemploRunnable(String name) {
        this.name = name;
    }

    //Lo que hace el metodo Run es definir la tarea que realiza cada hilo. imprime 5 mensajes con un retraso de 100 ms entre cada uno.
    @Override
    public void run() {
        for (int i = 1; i < 5; i++) {
            System.out.println(name + ", mensaje: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        //Se crean las instancias de Thread, cada una con el objeto del nombre de la clase (junto a su nombre).
        Thread hilo = new Thread(new EjemploRunnable("Hilo 1"));
        Thread hilo2 = new Thread(new EjemploRunnable("Hilo 2"));

        hilo.start();
        hilo2.start();
    }
}

package fp.dam.psp.CLASS.EvPrimera.TEMA2;

public class EjemploThread extends Thread{
    // La clase exttiende de otra clase denominada Thread
    private  String name;

    // El constructor recibe el nombre para identificar el hilo
    private EjemploThread(String name) {
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

    // El main se encarga de ejecutar los hilos
    public static void main(String[] args) {
        EjemploThread h = new EjemploThread("Hilo primero ");
        EjemploThread h2 = new EjemploThread("Hilo segundo ");
        EjemploThread h3 = new EjemploThread("Hilo tercero ");
        EjemploThread h4 = new EjemploThread("Hilo cuarto ");

        h.start();
        h2.start();
        h3.start();
        h4.start();

    }
}

package fp.dam.psp.EvPrimera.TEMA2.Dia20;

public class UnHilo extends Thread{
    //Debemos definir la tarea que va a realizar el hilo, esa tarea se implementa en el metodo run() siemore
    public UnHilo(int id) {
        super("hilo " + id);
    }

    //El método 'run()' define la tarea que realizará el hilo.
    @Override
    public void run() {
        // En este caso, imprime 5 mensajes con un retraso de 100 milisegundos entre cada uno.
        for (int i = 1; i <= 5; i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            System.out.println(getName() + ", mensaje " + i);
        }
    }


    public static void main(String[] args) {
        UnHilo h = new UnHilo(1);
        UnHilo h2 = new UnHilo(2);
        UnHilo h3 = new UnHilo(3);

    }
}


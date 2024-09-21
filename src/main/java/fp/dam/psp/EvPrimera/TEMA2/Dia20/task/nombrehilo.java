package fp.dam.psp.EvPrimera.TEMA2.Dia20.task;

public class nombrehilo extends Thread{
    private String n;

    public nombrehilo(String n) {
        this.n = n;
    }
    @Override
    public void run() {
        for (int i = 1; i < 5; i++) {
            System.out.println(getName() + ", mensaje: " + i);
        }
    }



    public static void main(String[] args) {
        nombrehilo n = new nombrehilo("h");
        n.run();
        n.start();

    }







}

package fp.dam.psp.CLASS.EvPrimera.TEMA2.Septiembre.Dia23;

public class Unhilo extends  Thread{
    public Unhilo(){
        super();
    }

    //Si poner todos los posibles constructores con super se invoca todo lo que se quiere
    public Unhilo (String name){ super(name);}
    public Unhilo (ThreadGroup group, String name){super(group,name);}
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName() + ", mensaje " + i);
        }
    }

    public static void main(String[] args) {
        Unhilo h = new Unhilo("yorkshire");
        h.start();
    }

}

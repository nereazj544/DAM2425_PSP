package fp.dam.psp.EvPrimera.TEMA2.Dia20;

//La tarea no se implementa en la clase princpal sino en otra secundaria
public class HiloMensajes extends Thread{
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            System.out.println(getName() + ", mensaje " + i);
        }
    }

}

package fp.dam.psp.EvPrimera.TEMA2.Septiembre.Dia20;

//La tarea no se implementa en la clase princpal sino en otra secundaria
public class HiloMensajes implements Runnable{
    private String nombre;
    public HiloMensajes(String nombre) {
        this.nombre = nombre;
    }

    @Override

    public void run() {
        for (int i = 1; i <= 5; i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            System.out.println(nombre + ", mensaje " + i);
        }
    }

}

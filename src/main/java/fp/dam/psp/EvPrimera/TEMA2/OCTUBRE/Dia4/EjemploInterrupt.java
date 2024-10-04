package fp.dam.psp.EvPrimera.TEMA2.OCTUBRE.Dia4;

public class EjemploInterrupt extends  Thread{
    public void run() {
        while (!isInterrupted())
            System.out.println("en el hilo");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println("interrumpido mientras dormia");
            //? Levantarse la bandera salta esto
            interrupt();
//       break;
       //? otra opcion
        }


    }
    public static void main(String[] args) throws InterruptedException {
        EjemploInterrupt h = new EjemploInterrupt();
        h.start(); //Se inicia el hilo
        Thread.sleep(2000);
        System.out.println(h.getState() +  " estado");
     h.interrupt();
        //? despues de un tiempo se interrumpe el hilo.
        //? Este metodo no interrumpe al hilo, lo que esta haciendo es que se señaliza, lo que hace activar una flag a ese hilo.
//        Thread.sleep(2000);
        h.join();
        System.out.println(h.getState() +  " estado");

        System.out.println("hilo finalizado");
    }
}

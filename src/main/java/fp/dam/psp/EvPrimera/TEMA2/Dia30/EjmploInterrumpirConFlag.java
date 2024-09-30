package fp.dam.psp.EvPrimera.TEMA2.Dia30;

public class EjmploInterrumpirConFlag extends Thread {
    private volatile boolean finalizar = false;
    //Lo que tiene de raro o peculiarar es el volatile,
    // al estar compartida por dos hilos > Hilo principal y el que se crea
    // y se inicia en el metodo principal.

    @Override
    public void run() {
        while (!finalizar) {
            System.out.println("en el Hilo");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("hilo finalizado");
    }

    public void finalizar() {
        finalizar = true;
    }
    public static void main(String[] args) throws InterruptedException {
        EjmploInterrumpirConFlag t = new EjmploInterrumpirConFlag();
        t.start();
        Thread.sleep(2000);
        t.finalizar();
    }
}

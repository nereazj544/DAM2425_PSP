package fp.dam.psp.CLASS.EvPrimera.TEMA2.Septiembre.Dia30;
class EjemploInterrumpirConFlagNoVolatile extends Thread {
    static boolean finalizar;

    @Override
    public void run() {
        while (!finalizar);
        System.out.println("hilo finalizado");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new EjemploInterrumpirConFlagNoVolatile();
        t.start();
        Thread.sleep(3000);
        finalizar = true;
        System.out.println("main finalizado");
    }
}

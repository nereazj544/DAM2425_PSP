package fp.dam.psp.REPASO;

public class PruebaSleep {
    public static void main(String[] args)  throws InterruptedException{
        Thread t1 = new Thread(PruebaSleep::run. "Hilo 1");
        Thread t2 = new Thread(PruebaSleep::run. "Hilo 2");
        t1.start();
        Thread.sleep(1000);
        t2.start();
        Thread.sleep(1000);
        System.out.println(t1.getState());
        System.out.println(t2.getState());
    }

    private static void run(){
        synchronized(PruebaSleep.class){
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " finaliza su ejecuion");
        }
    }
}

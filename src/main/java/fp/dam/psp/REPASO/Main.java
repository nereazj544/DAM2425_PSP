package fp.dam.psp.REPASO;

public class Main extends Thread {
    static Thread hiloPrincioal;

    public static void main(String[] args) {
        {
            hiloPrincioal = Thread.currentThread();
            Thread t = new Main();
            t.start();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        t.interrupt();
                    }
                    // hiloPrincioal.interrupt();
                    // t.interrupt();
                    // System.exit(0);
                }
            }.start();
            try {
                t.join();
            } catch (Exception e) {
                // System.exit(0);
            }
            // t.interrupt();
            // System.exit(0);
            System.out.println("Programa finalizado");

        }
    }
}
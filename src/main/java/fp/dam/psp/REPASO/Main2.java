package fp.dam.psp.REPASO;

public class Main2 {
    private static Contador c = new Contador(1000);

    private static void run() {
        for (int i = 0; i < 100; i++) {
            c.inc();
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                // TODO: handle exception
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(Main2::run);
        Thread t2 = new Thread(Main2::run);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Contador = " + c.n);
    }

    private static class Contador {
        public Integer n;

        public Contador(int n) {
            this.n = n;
        }

        public void inc() {
            synchronized (n) {
                if (n == 50)
                    try {
                        wait();
                    } catch (InterruptedException e) {

                    }
                else if (n > 200)
                    notify();

                n++;

            }
        }

    }
}

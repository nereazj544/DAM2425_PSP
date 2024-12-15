package fp.dam.psp.REPASO;

public class MyThread extends Thread {
    private Boolean suspendido = false;

    public void suspendido() {
        synchronized (this) {
            suspendido = true;
        }
    }

    public void reanudar() {
        synchronized (this) {
            suspendido = false;
            notify();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this) {

                    if (suspendido) {
                        System.out.println("suspendido");
                        wait();
                    }
                }
                System.out.println("trabajando");
                Thread.sleep(1000);

            } catch (Exception e) {
                // e.getMessage();
            }

        }

    }
}
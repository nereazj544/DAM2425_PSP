package T2_ProgMultiH.Ejemplos.InterrumpirHilos;

public class EjemploInterrupt extends Thread {
	public void run() {
//		Tarea tarea = new Tarea();
//		tarea.run();
		while (!interrupted()) {
//		while (Thread.currentThread().isInterrupted()) {
			System.out.println("en el Hilo");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				interrupt();
				System.out.println("interrumpido mientras dormía");
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		EjemploInterrupt h = new EjemploInterrupt();
		h.start();
		Thread.sleep(2000);
		h.interrupt();
		h.join();
		System.out.println("hilo finalizado");
	}
}

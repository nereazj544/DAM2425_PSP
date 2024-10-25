package T2_ProgMultiH.Ejemplos.InterrumpirHilos;

public class Tarea implements Runnable {

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			System.out.println("en el hilo");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				System.out.println("interrumpido mientras dormía");
				Thread.currentThread().interrupt();
			}
		}
	}
	
}

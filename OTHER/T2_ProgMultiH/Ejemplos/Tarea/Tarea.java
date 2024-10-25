package T2_ProgMultiH.Ejemplos.Tarea;

public class Tarea implements Runnable { //esta clase NO es un hilo
	//la funcionalidad si que va a asignarse a un hilo, pero la clase en si no es un hilo
	public void run() {
		for (int i = 1; i <= 5; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
			System.out.printf("%s, mensaje %d\n", Thread.currentThread().getName(), i);
		}
	}
}

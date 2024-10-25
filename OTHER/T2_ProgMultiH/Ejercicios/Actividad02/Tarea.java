package T2_ProgMultiH.Ejercicios.Actividad02;

public class Tarea implements Runnable { 
	
	int numM = 1;
	int waitTime = 1;
	
	Tarea(int numM, int waitTime) {
		this.numM = numM;
		this.waitTime = waitTime;
	}
	
	public void run() {
		for (int i = 1; i <= numM; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
			System.out.printf("%s, mensaje %d\n", Thread.currentThread().getName(), i);
		}
	}
}

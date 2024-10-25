package T2_ProgMultiH.Ejercicios.Actividad06;

public class Tarea implements Runnable { 
	
	int numM = 1;
	int waitTime = 1;
	int prio = 1;
	
	Tarea(int numM, int waitTime, int prio) {
		this.numM = numM;
		this.waitTime = waitTime;
		this.prio = prio;
	}
	
	public void run() {
		Thread.currentThread().setPriority(prio);
		for (int i = 1; i <= numM; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
			System.out.printf("%s, mensaje %d\n", Thread.currentThread().getName(), i);
		}
	}
}

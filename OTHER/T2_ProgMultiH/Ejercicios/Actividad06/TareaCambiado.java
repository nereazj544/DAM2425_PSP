package T2_ProgMultiH.Ejercicios.Actividad06;

import java.util.List;

public class TareaCambiado implements Runnable {

	int numH = 1;
	int numM = 1;
	int waitTime = 1;
	int[] prio = new int[numH];

	TareaCambiado(int numH, int numM, int waitTime, int[] prio) {
		this.numH = numH;
		this.numM = numM;
		this.waitTime = waitTime;
		this.prio = prio;
	}

	public void run() {
		ThreadGroup grupo = agrupar();
		Thread[] threads = null;
		grupo.enumerate(threads);
		for (int h = 0; h <= threads.length; h++) {
			threads[h].start();
			for (int i = 1; i <= numM; i++) {
				try {
					threads[h].sleep(waitTime);
				} catch (InterruptedException e) {

				}
				System.out.printf("%s, mensaje %d\n", threads[h].getName(), i);
			}
		}
	}

	public ThreadGroup agrupar() {
		ThreadGroup grupo = null;
		for (int i = 0; i < numH; i++) {
			new Thread(grupo, this, "hilo " + i + 1).setPriority(prio[i]);
			;

		}
		return grupo;
	}
}

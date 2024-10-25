package T2_ProgMultiH.Ejercicios.Actividad14_Locks;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {
	
	ReentrantLock lock;

	static final Random R = new Random();
	private final int plazas;
	private int ocupadas;

	public Banco(int plazas) {
		this.plazas = plazas;
	}

	public synchronized void sentarse() {
		while (ocupadas == plazas) {
			lock.lock();
			ocupadas++;
			System.out.println(Thread.currentThread().getName() + " se ha sentado.");
			try {
				Thread.sleep(R.nextLong(701)+100); //+1 para que este sentado un momento
				System.out.println(Thread.currentThread().getName() + " se ha levantado");
			} catch (InterruptedException e) {
			} finally {
				lock.unlock();
			}
		}
	}

}

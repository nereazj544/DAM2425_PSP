package T2_ProgMultiH.Ejercicios.BarberoDormilon_old;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Barberia {

	private final int numCli;
	private int numAsientos;

	public final Barbero barbero;
	public List<Thread> threadList;

	public final Semaphore SEMAPHORE;
	public ReentrantLock lock;

	public Barberia(int numCli, int numAsientos) {
		this.numCli = numCli;
		this.numAsientos = numAsientos;

		barbero = new Barbero();
		threadList = new ArrayList<>();

		SEMAPHORE = new Semaphore(this.numAsientos);
		lock = new ReentrantLock();
	}

	public static void main(String[] args) throws InterruptedException {
		Barberia barberia = new Barberia(10, 5);
		barberia.start();
	}

	private void start() throws InterruptedException {
		for (int i = 0; i < numCli; i++) {
//			Cliente c = new Cliente(i+1);
//			threadList.add(c);
			threadList.add(new Cliente(i + 1));
//			c.start();
//			c.join();
		}
		threadList.forEach(t -> t.start());
	}

	// METODOS BARBERO
	// ////////////////////////////////////////////////////////////////////////////////////////////

	public void afeitar(Cliente c) {
		if (!barbero.dormido) {
			lock.lock();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			} finally {
				c.afeitado = true;
				System.out.println("El barbero ha afeitado a " + c.getName());
				lock.unlock();
//			lock.notifyAll();
//			threadList.notify();
				siesta();
			}
		}

	}

	public void siesta() {
		System.out.println("El barbero está durmiendo");
		barbero.dormido = true;
//		try {
//			barbero.wait();
//		} catch (InterruptedException e) {
//		}
//		System.out.println("El barbero ha sido despertado");
	}

	// METODOS CLIENTE
	// ////////////////////////////////////////////////////////////////////////////////////////////

	public void despertarB(Cliente c) {
//		barbero.notify();
		barbero.dormido = false;
		System.out.println(c.getName() + " ha despertado al barbero.");
	}

	public void entrar(Cliente c) {
		if (!SEMAPHORE.tryAcquire()) {
			System.out.println("SALA LLENA: " + c.getName() + " se va.");
		} else {
			System.out.println(c.getName() + " entra a la barbería");
			if (barbero.dormido) {
//			if (barbero.isInterrupted()) {
				despertarB(c);
//				System.out.println(c.getName() + " despierta al barbero");
			}
			while (!c.afeitado) {
//				afeitar(c);
				System.out.println(c.getName() + " bucle while");
				if (lock.tryLock()) {
					afeitar(c);
					break;
				} else {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				}
			}
			System.out.println(c.getName() + " se va tras ser afeitado");
		}
	}

	// METODOS BARBERIA
	// ////////////////////////////////////////////////////////////////////////////////////////////

	public boolean salaLlena() {
		return SEMAPHORE.availablePermits() == 0;
	}

}

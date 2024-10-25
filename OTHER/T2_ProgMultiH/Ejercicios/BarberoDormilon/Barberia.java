package T2_ProgMultiH.Ejercicios.BarberoDormilon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Barberia {

	// variables barberia
	private final int numCli;
	private int numAs;
	public final Barbero barbero;

	// control threads
	ExecutorService executor;
	public final Semaphore SEMAPHORE;

	public List<Cliente> lista;

	public Barberia(int numCli, int numAs) {
		this.numCli = numCli;
		this.numAs = numAs;
		this.barbero = new Barbero(this);
//		this.barbero = new Barbero();
		this.SEMAPHORE = new Semaphore(this.numAs);
		executor = Executors.newFixedThreadPool(numCli);
		lista = new ArrayList<>();
	}

	public static void main(String[] args) throws InterruptedException {
		Barberia barberia = new Barberia(10, 5);
		barberia.start();
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////

	private void start() throws InterruptedException {
		executor.submit(barbero);
		for (int i = 0; i < numCli; i++) {
			Cliente c = new Cliente(i + 1, this);
			executor.submit(c);
		}
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.NANOSECONDS);
	}

	public boolean salaVacia() { 
		return SEMAPHORE.availablePermits() == numAs;
	}

}

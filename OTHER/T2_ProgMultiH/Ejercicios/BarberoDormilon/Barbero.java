package T2_ProgMultiH.Ejercicios.BarberoDormilon;

public class Barbero extends Thread {

	private Barberia barberia;
	public boolean dormido;

	public Barbero(Barberia barberia) {
		this.barberia = barberia;
		dormido = false;
	}

	@Override
	public void run() {
		while (!barberia.executor.isShutdown()) {
			afeitar(barberia.lista.get(0));
			siesta();
		}
	}

	public synchronized void afeitar(Cliente cliente) {
		if (!dormido) {

			barberia.SEMAPHORE.release();
			System.out.println("El barbero afeita a un cliente (" + cliente.getName() + ")");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
			}
			siesta();
		}
	}

	public void siesta() {
		dormido = true;
		if (!barberia.salaVacia()) {
			System.out.println("El barbero duerme la siesta");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void despertarB(Cliente c, Barbero b) {
		if (dormido) {
			b.notifyAll();
			System.out.println("Un cliente ha despertado al barbero (" + c.getName() + ")");
			dormido = false;
		}
	}

}

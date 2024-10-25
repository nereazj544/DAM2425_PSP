package T2_ProgMultiH.Ejercicios.BarberoDormilon_Swing;

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
			Main.getAfeitado().setText(cliente.getName());
			barberia.SEMAPHORE.release();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
			}
			siesta();
		}
	}

	public void siesta() {
		Main.barbero.setText("DORMIDO");
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
			Main.barbero.setText("DESPIERTO");
			dormido = false;
		}
	}

}

package T2_ProgMultiH.Ejemplos.WorkerThreads;

public class Persona extends Thread {

	private Parque parque;

	public Persona(int id, Parque parque) {
		super("Persona "+ id);
		this.parque = parque;
	}

	public void run() {
		try {
			do {
				System.out.println(getName() + " pasea por el parque");
				Thread.sleep((long) Math.random() * 2000 + 1000);
				System.out.println(getName() + " llega al banco");
			} while (!parque.sentarseEnElBanco());
			parque.levantarseDelBanco();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

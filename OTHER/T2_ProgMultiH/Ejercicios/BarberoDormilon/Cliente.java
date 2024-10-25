package T2_ProgMultiH.Ejercicios.BarberoDormilon;

public class Cliente extends Thread {

	private final int id;
	private final Barberia barberia;
	public boolean afeitado;

	public Cliente(int id, Barberia barberia) {
		super("Cliente " + id);
		this.id = id;
		this.barberia = barberia;
	}

	@Override
	public void run() {
//		barberia.entrar(this);
		barberia.lista.add(this);
		if (barberia.SEMAPHORE.tryAcquire()) {
			System.out.println(
					"Entrar(" + this.getName() + ") ; AVAILABLE PERMITS = " + barberia.SEMAPHORE.availablePermits());
			barberia.barbero.despertarB(this, barberia.barbero);
			barberia.barbero.afeitar(this);
			System.out.println("El cliente se va (" + this.getName() + ") ; AVAILABLE PERMITS = "
					+ barberia.SEMAPHORE.availablePermits());
//			barberia.barbero.siesta();
		} else {
			System.out.println("SEMAPHORE NOT AQUIRED (" + this.getName() + ")");
		}
		barberia.lista.remove(this);
//		System.out.println("BARBERIA.LISTA.SIZE = " + barberia.lista.size());
	}
	
	

}

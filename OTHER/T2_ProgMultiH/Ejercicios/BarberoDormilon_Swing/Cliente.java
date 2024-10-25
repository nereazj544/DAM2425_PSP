package T2_ProgMultiH.Ejercicios.BarberoDormilon_Swing;

import javax.swing.JTextField;

public class Cliente extends Thread {

	private final int id;
	private final Barberia barberia;
	public boolean afeitado;
	public JTextField asiento, calle;

	public Cliente(int id, Barberia barberia) {
		super("Cliente " + id);
		this.id = id;
		this.barberia = barberia;
	}

	@Override
	public void run() {
//		barberia.entrar(this);
//		calle = Main.getCalle();
		barberia.lista.add(this);
		if (barberia.SEMAPHORE.tryAcquire()) {
			asiento = Main.getAsiento(this);
//			System.out.println("Entrar(" + this.getName() + ") ; AVAILABLE PERMITS = " + barberia.SEMAPHORE.availablePermits());
			barberia.barbero.despertarB(this, barberia.barbero);
			barberia.barbero.afeitar(this);
			Main.setAsiento(this);
//			System.out.println("El cliente se va (" + this.getName() + ") ; AVAILABLE PERMITS = "
//					+ barberia.SEMAPHORE.availablePermits());
//			asiento.setText("");
//			Main.AsientosQueue.add(asiento);
//			barberia.barbero.siesta();
		} else {
			Main.jarea.append(getName());
//			System.out.println("SEMAPHORE NOT AQUIRED (" + this.getName() + ")");
		}
		barberia.lista.remove(this);
//		System.out.println("BARBERIA.LISTA.SIZE = " + barberia.lista.size());
	}
	
	

}

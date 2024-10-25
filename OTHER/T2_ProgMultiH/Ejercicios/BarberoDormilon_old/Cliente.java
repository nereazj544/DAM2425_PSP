package T2_ProgMultiH.Ejercicios.BarberoDormilon_old;

public class Cliente extends Thread {
	
	private final int id;
	private final Barberia barberia;
	public boolean afeitado;

	public Cliente(int id) {
		super("Cliente " + id);
		this.id = id;
		afeitado = false;
		barberia = new Barberia(10, 5);
	}
	
	@Override
	public void run() {
		barberia.entrar(this);
		
//		if (!(barberia.SEMAPHORE.tryAcquire())) {
//			System.out.println("SALA LLENA: " + this.getName() + " se va.");
//		} else {
//			System.out.println(this.getName() + " entra a la barbería");
//			if (barberia.barbero.isInterrupted()) {
//				barberia.despertarB(this);
//				System.out.println(this.getName() + " despierta al barbero");
//			}
//			while (barberia.lock.tryLock()) {
//				barberia.afeitar(this);
//				System.out.println(this.getName() + " bucle while");
//			}
//			System.out.println(this.getName() + " se va tras ser afeitado");
//		}
		
//		if (barberia.SEMAPHORE.tryAcquire()) {
//			System.out.println("SALA LLENA: " + this.getName() + " se va.");
//		} else {
//			System.out.println(this.getName() + " entra a la barbería");
//			if (barberia.barbero.isInterrupted()) {
//				barberia.despertarB(this);
//				System.out.println(this.getName() + " despierta al barbero");
//			}
//			barberia.afeitar(this);
//			System.out.println(this.getName() + " se va tras ser afeitado");
//		}
	}
	
}

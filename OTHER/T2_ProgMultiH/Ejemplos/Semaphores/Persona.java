package T2_ProgMultiH.Ejemplos.Semaphores;

public class Persona extends Thread {

	private Parque parque;

	public Persona(int id, Parque parque) {
		super("Persona "+ id);
		this.parque = parque;
	}
	
	public void pasear() {
		System.out.println("El banco está lleno, " + getName() + " pasea por el parque");
		try {
			Thread.sleep((long) Math.random() * 2000 + 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(getName() + " llega al banco de nuevo");
	}

	public void run() {
		try {
			System.out.println(getName() + " pasea por el parque");
			Thread.sleep((long) Math.random() * 2000 + 1000);
			System.out.println(getName() + " llega al banco");
			
			//mi codigo
			while (!parque.sentarseEnElBanco()) pasear();
			
			//profe 
			/*
			do {
				System.out.println(getName() + " pasea por el parque");
				Thread.sleep((long) Math.random() * 2000 + 1000);
				System.out.println(getName() + "llega al banco");
			} while (!parque.sentarseEnElBanco());
			 */
			
			parque.levantarseDelBanco();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

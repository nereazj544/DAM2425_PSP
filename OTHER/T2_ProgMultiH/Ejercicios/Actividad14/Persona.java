package T2_ProgMultiH.Ejercicios.Actividad14;

import java.util.Random;

public class Persona extends Thread { 
	
	static final Random R = new Random();
	private Banco banco;
	
	public Persona(Banco banco, String nombre) {
		super(nombre);
		this.banco = banco;
	}
	
	@Override
	public void run() {
		System.out.println(getName() + " está paseando.");
		try {
			Thread.sleep(R.nextLong(2001) + 1000); //+1 para que esté sentado un momento lol
		} catch (InterruptedException e) {}
		System.out.println(getName() + " ha llegado al banco.");
		banco.sentarse();
		System.out.println(getName() + " se va del parque");
	}
	
}

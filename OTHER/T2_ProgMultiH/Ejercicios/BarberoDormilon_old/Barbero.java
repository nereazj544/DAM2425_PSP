package T2_ProgMultiH.Ejercicios.BarberoDormilon_old;

public class Barbero extends Thread {
	
	private Barberia barberia;
	public boolean dormido;
	
	public Barbero() {
		super("Barbero");
		dormido = false;
//		barberia = new Barberia(10, 5);
	}
	
	@Override
	public void run() {
//		while (!barberia.shutdown()) {
//			barberia.siesta();
//		}
	}
	
}

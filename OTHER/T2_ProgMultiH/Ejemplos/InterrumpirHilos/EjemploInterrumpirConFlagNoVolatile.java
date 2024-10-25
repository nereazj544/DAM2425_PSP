package T2_ProgMultiH.Ejemplos.InterrumpirHilos;

public class EjemploInterrumpirConFlagNoVolatile extends Thread {
	static boolean finalizar; //no termina porque no es volatile
	
	@Override
	public void run() {
		while (!finalizar) ;
		System.out.println("hilo finalizado");
	}
	
	public static void main(String[] args) throws InterruptedException {
		EjemploInterrumpirConFlagNoVolatile t = new EjemploInterrumpirConFlagNoVolatile();
		t.start();
		Thread.sleep(2000);
		finalizar = true;
		System.out.println("main finalizado");
	}
}

package T2_ProgMultiH.Ejemplos.InterrumpirHilos;

public class EjemploInterrumpirConFlag extends Thread {
	private volatile boolean finalizar = false; //es importante lo de volatile??? mirar ejemplo NoVolatile
		//en este ejemplo especificamente el volatile no afecta, pero suele ser necesario
	
	@Override
	public void run() {
		while (!finalizar) {
			System.out.println("en el Hilo");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				
			}
		}
		System.out.println("hilo finalizado");
	}
	
	public void finalizar() {
		finalizar = true;
	}
	
	public static void main(String[] args) throws InterruptedException {
		EjemploInterrumpirConFlag t = new EjemploInterrumpirConFlag();
		t.start();
		Thread.sleep(2000);
		t.finalizar();
	}
}

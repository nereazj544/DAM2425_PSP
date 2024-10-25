package T2_ProgMultiH.Ejemplos.InterrumpirHilos;

public class EjemploInterrumpirConFlagQM extends Thread {
	private boolean finalizar = false; // es importante lo de volatile??? mirar ejemplo NoVolatile

	@Override
	public void run() {
		while (true) {
			while (!finalizar) {
				System.out.println("en el Hilo");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {

				}
			}
			System.out.println("hilo finalizado");
		}
	}

	public void finalizar() throws InterruptedException {
		finalizar = true;
		Thread.sleep(300);
		finalizar = false;
	}

	public static void main(String[] args) throws InterruptedException {
		EjemploInterrumpirConFlagQM t = new EjemploInterrumpirConFlagQM();
		t.start();
		Thread.sleep(2000);
		t.finalizar();
	}
}

package T2_ProgMultiH.Ejemplos;

public class EjemploJoin {
	public static void main(String[] args) throws InterruptedException {
		Thread hilo = new Thread("cuenta atrás") {
			@Override
			public void run() {
				for (int i = 5; i>=0; i--) {
					System.out.println(i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
				}
				System.out.println("hilo finalizado");
				
			}
		};
		hilo.start();
		hilo.join(); //esto hace que el main espere a que finalice este???
		System.out.println("metodo main finalizado");
	}
}

package T2_ProgMultiH.Ejemplos.UnHilo;

public class MainEjercicio1 {
	public static void main(String[] args) {
		for (int i = 1; i<=3; i++) {
			new Thread("hilo " + i) { //ESTO ES UNA CLASE ANONIMA (aka una clase dentro de una clase???)
				@Override
				public void run() {
					for (int i = 1; i<=5; i++) {
						try {
							Thread.sleep(100); 
						} catch (InterruptedException e) {
							
						}
						System.out.println(getName() + ", mensaje:" + i);
					}
				}
			}.start();
		}
	}
}

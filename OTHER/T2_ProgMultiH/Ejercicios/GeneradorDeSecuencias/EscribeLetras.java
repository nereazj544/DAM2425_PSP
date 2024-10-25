package T2_ProgMultiH.Ejercicios.GeneradorDeSecuencias;

public class EscribeLetras extends Thread {

	public final char letra;
	public final int num;

	public EscribeLetras(char letra, int num) {
		super("" + letra + num);
		this.letra = letra;
		this.num = num;
	}

	@Override
	public void run() {
		while (true) {
			Main.imprimir(this);
			synchronized (this) {
				Main.esperar(this);
			}
//			try {
//				Main.esperar(this);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}

}

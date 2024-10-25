package T2_ProgMultiH.Ejercicios.GeneradorDeSecuenciasEXAMEN;

public class EscribeLetras extends Thread {

	public final char letra;
	public final int num;

	public EscribeLetras(char letra, int num) {
		super("" + letra + num);
		this.letra = letra; //letra a imprimir
		this.num = num; //número de veces
	}

	@Override
	public void run() {
		while (true) { //bucle en el que imprimen, y esperan a su turno
			Main.imprimir(this);
				try {
					Main.esperar(this);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//			}
		}
	}

}

package T2_ProgMultiH.Ejemplos.Fumadores;

public class Fumador extends Thread {

	@Override
	public void run() {
		while (true) {
			Ingrediente in = Ingrediente.randomIng();
			Mesa mesa = new Mesa();
			mesa.retirar(in);
		}
	}

}

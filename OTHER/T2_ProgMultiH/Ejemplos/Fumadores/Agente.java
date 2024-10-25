package T2_ProgMultiH.Ejemplos.Fumadores;

public class Agente extends Thread {

	@Override
	public void run() {
		while (true) {
			Ingrediente in1 = Ingrediente.randomIng();
			Ingrediente in2 = Ingrediente.randomIng();
			Mesa mesa = new Mesa();
			mesa.colocar(in1, in2);
		}
	}

}

package T2_ProgMultiH.Ejemplos.Fumadores;

public class Main {

	public static void main(String[] args) {
		Agente agente = new Agente();
		for (int i = 0; i<3; i++) {
			Fumador fumador = new Fumador();
			fumador.start();
		}
		agente.start();
	}

}

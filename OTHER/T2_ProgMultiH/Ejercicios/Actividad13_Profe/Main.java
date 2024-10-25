package T2_ProgMultiH.Ejercicios.Actividad13_Profe;

public class Main {
	public static void main(String[] args) {
		Almacen almacen = new Almacen(15);
		for (int i = 0; i < 30; i++) {
			Productor productor = new Productor(almacen, 100);
			Consumidor consumidor = new Consumidor(almacen, 100);
			productor.start();
			consumidor.start();
		}
	}
}
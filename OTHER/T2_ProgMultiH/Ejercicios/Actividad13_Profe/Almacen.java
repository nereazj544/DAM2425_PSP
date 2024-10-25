package T2_ProgMultiH.Ejercicios.Actividad13_Profe;

public class Almacen {
	private int almacenados = 0;
	private String[] productos;

	public Almacen(int capacidad) {
		productos = new String[capacidad];
	}

	public synchronized void almacenar(String producto) {
		while (almacenados == productos.length) // almac�n lleno
			try {
				wait();
			} catch (InterruptedException e) {
			}
		productos[almacenados++] = producto;
		notify();
	}

	public synchronized String retirar() {
		while (almacenados == 0) // almac�n vac�o
			try {
				wait();
			} catch (InterruptedException e) {
			}
		String producto = productos[--almacenados];
		notify();
		return producto;
	}
}
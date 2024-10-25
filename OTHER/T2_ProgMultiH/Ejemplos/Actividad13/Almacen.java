package T2_ProgMultiH.Ejemplos.Actividad13;

public class Almacen {
	private int almacenados = 0;
	private String[] productos;

	public Almacen(int capacidad) {
		productos = new String[capacidad];
	}

	public synchronized void almacenar(String producto) {
		if (almacenados == productos.length) // almac�n lleno
			try {
				wait();
			} catch (InterruptedException e) {
			}
		productos[almacenados++] = producto;
		notify();
	}

	public synchronized String retirar() {
		if (almacenados == 0) // almac�n vac�o
			try {
				wait();
			} catch (InterruptedException e) {
			}
		String producto = productos[--almacenados];
		notify();
		return producto;
	}
}
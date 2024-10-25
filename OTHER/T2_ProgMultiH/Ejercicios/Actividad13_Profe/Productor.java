package T2_ProgMultiH.Ejercicios.Actividad13_Profe;

public class Productor extends Thread {
	private long retardo, contador = 0;
	private Almacen almacen;

	public Productor(Almacen almacen, long retardo) {
		super("productor");
		this.retardo = retardo;
		this.almacen = almacen;
	}

	public void run() {
		while (true) {
			String producto = String.format("%d", ++contador);
			almacen.almacenar(producto);
			System.out.println("producto " + producto + " almacenado");
			try {
				Thread.sleep(retardo);
			} catch (InterruptedException e) {
			}
		}
	}
}


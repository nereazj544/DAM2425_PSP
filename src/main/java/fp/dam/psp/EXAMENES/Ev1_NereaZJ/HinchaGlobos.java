package fp.dam.psp.EXAMENES.Ev1_NereaZJ;

public class HinchaGlobos extends Thread {
	
	Globo g;
	Deposito deposito;
	String nombre;
	int id;
	static boolean s = false; // Suspendido
	static boolean f = false; // matar hilo

// ! ======= METODOS PARA FINALIZAR LOS HILOS =====
	public synchronized void suspender() {
		s = true;
	}

	public synchronized void reanudar() {
		s = false;
		notify();
	}

	public synchronized void fin() {
		f = true;
		interrupt();
	}
	
	public HinchaGlobos(String nombre, Deposito deposito) {
		this.nombre = "HG" + id; //Nombre seguido del ID del hinchador
		this.deposito = deposito;
	}

	@Override
	public void run() {
		while (!f) {
			synchronized (this) {
				while (s) {

					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
			g.HINCHAR();

		}
	}

}

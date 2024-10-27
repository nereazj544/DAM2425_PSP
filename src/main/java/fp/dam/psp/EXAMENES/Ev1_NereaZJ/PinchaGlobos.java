package fp.dam.psp.EXAMENES.Ev1_NereaZJ;

public class PinchaGlobos extends Thread {
	
	Globo  g;
	Deposito deposito;
	String nombre;
	static boolean s = false; // Suspendido
	static boolean f = false; // matar hilo
	
	
	public PinchaGlobos(String nombre, Deposito deposito) {
		this.nombre = "PG";
		this.deposito = deposito;
	}

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
			
			
			g.REVENTAR();
			
			
		}
	}

	
	
	
	


}

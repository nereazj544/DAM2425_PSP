package fp.dam.psp.EXAMENES.Ev1_NereaZJ;


public class Deposito {
	int MAX = 10;
	int MIN = 3;
	int GDis = MAX;
	int GHin = 0;
	Globo globo;
	HinchaGlobos hg;
	int capacidad;

	public Deposito(int capacidad) {
	
		this.capacidad = capacidad;
	}

	public synchronized void enregar(String hincha) {
		while (GDis == 0) {
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
			}
			GDis--;
			int num = MAX - GDis;
//			actualizar("Entregado a HG " + hg.id + "\n");

		}
	}

	public synchronized void Hinchar() {
		while (GHin >= MAX) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				e.getMessage();
			}

		}

	}

	public synchronized void EmHinchar() {
		try {
			wait();
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		GHin++;
	}

	public synchronized void Pinchar() {
		try {
			wait();
		} catch (Exception e) {
			// TODO: handle exception
		}
		GHin--;
		notifyAll();
	}

	public synchronized boolean hayGlHin() {
		return GHin > 0;
	}

	public synchronized boolean hayGlDis() {
		return GDis > 0;
	}

}

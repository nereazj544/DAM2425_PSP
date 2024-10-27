package fp.dam.psp.EXAMENES.Ev1_NereaZJ;

import static fp.dam.psp.EXAMENES.Ev1_NereaZJ.Main.actualizar;

public class Globo {
	int vl = 0;
	Deposito deposito;
	String n;
	int id;

	public Globo(int vl, String n) {
		this.vl = vl;
		this.n = "Globo " + id;
	}

	HinchaGlobos HG;
	PinchaGlobos PG;

	public synchronized void HINCHAR() {

		try {

			while (vl < 5) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				vl++;

				int num = deposito.enregar(n);

				actualizar("Globo " + num + "Vl " + vl);
				if (Math.random() < 0.2) {
					actualizar("Globo " + num + "Estalla\n");
				} else if (vl == 5) {
					actualizar("Globo" + num + " Entregado a " + HG.id);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public synchronized void REVENTAR() {
		if (deposito.hayGlDis()) {
			int num = deposito.enregar(n);
			deposito.Pinchar();
			while (vl < 5) {
				Thread.sleep(100);

			}
			if (Math.random() < 0.2) {
				actualizar("Globo " + num + " PINCHADO POR " + PG.nombre);
			}
		}

	}

}

package fp.dam.psp.CLASS.EvPrimera.TEMA2.OCTUBRE.Dia21.Fumadores.AnotherVersion;
import static fp.dam.psp.CLASS.EvPrimera.TEMA2.OCTUBRE.Dia21.Fumadores.AnotherVersion.Main.actualizar;
public class Fumador extends Thread {
	Ingrediente ingrediente;
	Mesa mesa;

	public Fumador(String nombre, Ingrediente ingrediente, Mesa mesa) {
		super(nombre);
		this.ingrediente = ingrediente;
		this.mesa = mesa;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				}
			}

			mesa.retirar(ingrediente);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
			}
			actualizar(getName() + " terminó de fumar\n");
			actualizar(getName() + " finaliza su tarea");
		}
		// TODO quitar el comentario de la línea siguiente cuando se pueda finalizar el
		// hilo (es decir, cuando el bucle ya no sea infinito)
		// actualizar (getName() + " finaliza su tarea");
	}
}

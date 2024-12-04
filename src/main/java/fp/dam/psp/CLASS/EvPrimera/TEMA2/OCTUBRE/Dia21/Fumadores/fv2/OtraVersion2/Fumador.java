package fp.dam.psp.CLASS.EvPrimera.TEMA2.OCTUBRE.Dia21.Fumadores.fv2.OtraVersion2;

import static fp.dam.psp.CLASS.EvPrimera.TEMA2.OCTUBRE.Dia21.Fumadores.fv2.OtraVersion2.Main.actualizar;

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
			// ! CODIGO AÑADIDO.

			synchronized (this) {
				try {
					wait(); // Esperamos a que sea notificado por el Mian
				} catch (InterruptedException e) {
				}
			}
			// ! CODIGO INICAL
			mesa.retirar(ingrediente);
			try {
				sleep(1000);
			} catch (InterruptedException e) {

			}
			actualizar(getName() + " terminó de fumar\n");
			actualizar(getName() + " finaliza su tarea\n");
		}
		// TODO quitar el comentario de la línea siguiente cuando se pueda finalizar el
		// hilo (es decir, cuando el bucle ya no sea infinito)
	}
}

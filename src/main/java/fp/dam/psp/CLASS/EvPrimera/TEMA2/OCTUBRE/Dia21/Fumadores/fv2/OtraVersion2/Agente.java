package fp.dam.psp.CLASS.EvPrimera.TEMA2.OCTUBRE.Dia21.Fumadores.fv2.OtraVersion2;

import static fp.dam.psp.CLASS.EvPrimera.TEMA2.OCTUBRE.Dia21.Fumadores.fv2.OtraVersion2.Main.actualizar;

public class Agente extends Thread{

	private Mesa mesa;
	
	public Agente(Mesa mesa) {
		super("Agente");
		this.mesa = mesa;
	}
	
	@Override
	public void run() {
		while (true) {
		//! CODIGO AÑADIDO.

			synchronized(this){
				try {
					wait(); //Esperamos a que sea notificado por el Mian
				} catch (InterruptedException e) {
					
				}
			}
			//! CODIGO INCIAL
			//Implementa los ingredientes
			Ingrediente i1 = Ingrediente.get();
			Ingrediente i2 = Ingrediente.get();
			while(i1==i2)
				i2 = Ingrediente.get();
			mesa.depositar(i1, i2);
			actualizar ("El agente finaliza su tarea\n"); //Info de que ha termidado
		}
		// TODO quitar el comentario de la línea siguiente cuando se pueda finalizar el hilo (es decir, cuando el bucle ya no sea infinito)
	}
}

package T2_ProgMultiH.Ejercicios;

import java.util.Scanner;

public class Actividad09 extends Thread {
	
	static int contador = 0;
	
	public void run() {
		while (!interrupted()) {
			contador++;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				interrupt();
				System.out.println("interrumpido mientras dorm�a");
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Actividad09 h = new Actividad09();
		h.start();
		System.out.println("Introduzca \"fin\" para parar el contador");
		while (!scanner.nextLine().equalsIgnoreCase("fin")) ;
		scanner.close();
		h.interrupt();
		h.join();
		System.out.println("hilo finalizado");
		System.out.println("contador: " + contador);
	}
}

/*
	S�, ya que cuando se interrumpe salta la excepci�n
	--
	
 */


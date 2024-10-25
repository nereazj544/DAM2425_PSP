package T2_ProgMultiH.Ejercicios.GeneradorDeSecuenciasEXAMEN;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

	public static ReentrantLock lock = new ReentrantLock();
	static ExecutorService ex;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); 
		String linea = scanner.nextLine(); //se leen los patrones
		String[] array = linea.split(""); //se separan los patrones, dos posiciones en el array (0 y 1, por ejemplo) corresponden a un patron
		try {
			for (int i = 0; i < array.length; i += 2) { //se suman dos para que se lean las dos variables del patron
				ex = Executors.newSingleThreadExecutor();
				EscribeLetras el = new EscribeLetras(array[i].charAt(0), Integer.parseInt(array[i + 1])); //las dos variables del hilo
				ex.execute(el);
			}
		} catch (Exception e) { //en caso de que el patron sea incorrecto
			System.out.println("SE HA INTRODUCIDO UN PATRON INCORRECTO");
			System.exit(0);
		}
		scanner.close();
	}

	public synchronized static void esperar(EscribeLetras el) throws InterruptedException {
			el.wait(); //
			System.out.println(el.getName());
	}

	public static void imprimir(EscribeLetras el) {
		lock.lock();
		for (int i = 0; i < el.num; i++) {
			System.out.println(el.letra);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		lock.unlock();
		ex.notifyAll(); //se notifican todas
	}
}

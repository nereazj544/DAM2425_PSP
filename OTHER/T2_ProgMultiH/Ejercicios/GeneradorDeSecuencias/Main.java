package T2_ProgMultiH.Ejercicios.GeneradorDeSecuencias;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
	
	public static List<EscribeLetras> lista;
	public static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		lista = new ArrayList<>();
		String linea = scanner.nextLine();
		String[] array = linea.split("");
		for (int i = 0; i<array.length; i+=2) {
			ExecutorService ex = Executors.newSingleThreadExecutor();
			EscribeLetras el = new EscribeLetras(array[i].charAt(0), Integer.parseInt(array[i+1]));
			ex.execute(el);
			lista.add(el);
//			el.start();
		}
		scanner.close();
	}
	
	public synchronized static void esperar() throws InterruptedException {
//		Thread.wait();
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
//		lista.notify();
	}

	public static void esperar(EscribeLetras escribeLetras) {
		
	}
}

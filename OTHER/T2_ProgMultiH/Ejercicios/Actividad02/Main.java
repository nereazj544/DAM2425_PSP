package T2_ProgMultiH.Ejercicios.Actividad02;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Introduce el número de hilos");
		int numH = scanner.nextInt();
		System.out.println("Introduce el número de mensajes");
		int numM = scanner.nextInt();
		System.out.println("Introduce el tiempo de espera (en milisegundos)");
		int waitTime = scanner.nextInt();
		Runnable tarea = new Tarea(numM, waitTime);
		for (int i = 1; i<=numH; i++) 
			new Thread(tarea, "hilo " + i).start();
		scanner.close();
	}
}

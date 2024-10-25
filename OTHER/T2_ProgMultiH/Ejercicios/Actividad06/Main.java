package T2_ProgMultiH.Ejercicios.Actividad06;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Introduce el número de hilos");
		int numH = scanner.nextInt();
		System.out.println("Introduce (en orden) la prioridad de los hilos");
		System.out.println("(10 será la máxima prioridad y 1 la mínima)");
		int[] prio = new int[numH];
		for (int i = 0; i<numH; i++) {
			prio[i] = scanner.nextInt();
		}
		System.out.println("Introduce el número de mensajes");
		int numM = scanner.nextInt();
		System.out.println("Introduce el tiempo de espera (en milisegundos)");
		int waitTime = scanner.nextInt();
		for (int i = 1; i<=numH; i++) {
			Runnable tarea = new Tarea(numM, waitTime, prio[i-1]);
			new Thread(tarea, "hilo " + i).start();
		}
		
//		for (int i = 1; i<=numH; i++) {
//			Runnable tarea = new TareaCambiado(numH, numM, waitTime, prio);
//			new Thread(tarea, "hilo " + i).start();
//		}
		scanner.close();
	}
}

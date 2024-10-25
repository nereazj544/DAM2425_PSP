package T2_ProgMultiH.Ejemplos.Tarea;

public class Main {
	public static void main(String[] args) {
		Runnable tarea = new Tarea();
		for (int i = 1; i<=3; i++) 
			new Thread(tarea, "hilo " + i).start();
	}
}

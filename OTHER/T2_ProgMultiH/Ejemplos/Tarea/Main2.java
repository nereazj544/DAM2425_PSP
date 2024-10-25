package T2_ProgMultiH.Ejemplos.Tarea;

public class Main2 {
	public static void tarea() {
		for (int i = 1; i<=5; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
			System.out.printf("%s, mensaje %d\n", Thread.currentThread().getName(), i);
		}
	}
	
	public static void main(String[] args) {
		for (int i = 1; i<=3; i++) 
			new Thread(Main2::tarea, "hilo " + i).start(); 
				//esta referencia a método funciona porque runnable es una interface funcional; aka si le paso una referencia al metodo como el metodo run (aka no requiere parametros formales, y no devuelve nada) y funcionara anyways
		
	}
}

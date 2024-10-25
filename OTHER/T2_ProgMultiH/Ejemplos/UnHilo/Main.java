package T2_ProgMultiH.Ejemplos.UnHilo;

public class Main {
	public static void main(String[] args) {
		for (int i = 1; i<=3; i++)
			new UnHilo(i).start(); //este comando está iniciando 3 hilos distintos
		//cuando instanciemos un hilo, NO SE INVOCA A RUN, se invoca a .start(); !!!
	}
	
	//el resultado demuestra que se ejecutan paralelamente, aunque se impriman unos hilos antes que otros
	//la razon por la que salen en distinto orden es debido a que la pantalla es un recurso compartido
		//es decir, solo un hilo puede acceder a la pantalla a la vez
	//si ejecutamos el código varias veces, es poco probable que nos salga el mismo orden

	//durante la ejecución de esto, se crean 4 hilos (main, y los 3 que creamos)
	
}

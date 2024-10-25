package T2_ProgMultiH.Ejemplos.UnHilo;

public class UnHilo extends Thread { 
	public UnHilo(int id) {
		super("hilo " + id);
		//con esto invocamos al constructor Thread(String name)
	}
	
	@Override
	public void run() { //extendemos el método run para definir la funcionalidad del hilo (aka qué va a hacer)
		for (int i = 1; i<=5; i++) {
			try {
				Thread.sleep(100); 
			} catch (InterruptedException e) {
				//no ha explicado por que no se pone, pero bueno :b dijo que lo explicaria en algun momento
				//"sabemos que en este programa ((en especifico)) no se va a lanzar nunca la excepción"
				//ponemos un try/catch en vez de un throws porque 
				//no ponemos un throws en este método debido a que es un metodo heredado
					//si pusiesemos el throws, daria error
				//se puede poner el throws en el metodo main que ejecuta a esta clase tho
			}
			System.out.println(getName() + ", mensaje:" + i);
		} //este for duerme 100 milisegundos e imprime un número
	}
	//cuando instanciemos un hilo, NO SE INVOCA A RUN, se invoca a .start(); !!!
}

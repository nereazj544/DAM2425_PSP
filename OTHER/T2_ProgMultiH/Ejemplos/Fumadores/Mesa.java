package T2_ProgMultiH.Ejemplos.Fumadores;

public class Mesa {
	
	private Ingrediente ingrediente1;
	private Ingrediente ingrediente2;
	
	public synchronized void retirar(Ingrediente ingrediente) {
		System.out.println("Alguien intenta retirar");
		while (ingrediente == ingrediente1 || ingrediente == ingrediente2) 
			try {
				wait();
			} catch (InterruptedException e) {
			}
		ingrediente1 = ingrediente2 = null;
		System.out.println("Se retiran los ingredientes de la mesa");
		try {
			wait(1000);
		} catch (InterruptedException e) {
		}
		notifyAll();
	}
	
	public synchronized void colocar(Ingrediente ingrediente1, Ingrediente ingrediente2) {
		while (this.ingrediente1 != null & this.ingrediente2 != null)
			try {
				wait();
			} catch (InterruptedException e) {
			}
		this.ingrediente1 = ingrediente1; 
		this.ingrediente2 = ingrediente2; 
		System.out.println("Se colocan ingredientes en la mesa");
		notifyAll();
	}
	
}

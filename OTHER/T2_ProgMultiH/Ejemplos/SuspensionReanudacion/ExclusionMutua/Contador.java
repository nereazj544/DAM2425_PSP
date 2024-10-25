package T2_ProgMultiH.Ejemplos.SuspensionReanudacion.ExclusionMutua;

public class Contador {
	private int n;
	
	public Contador (int n) {
		this.n = n;
	}
	
//	public void inc() { //SIN SINCRONIZAR
	public synchronized void inc() {
		n++; //SECCION CRITICA 
	}
	
	//MIRAR MONITOR DE _ SINCRONIZADO EN LOS APUNTES MIOS
	
	//tambien se puede hacer
	public void inc2() {
		synchronized (this) { //NO PONER OBJETO MUTABLE COMO MONITOR, NO ES RECOMENDABLE (en este caso funcionaria tho)
			n++;
		}
	}
	
	public int get() {
		return n;
	}
}

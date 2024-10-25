package T2_ProgMultiH.Ejemplos.Fumadores;

public enum Ingrediente {
	
	TABACO, PAPEL, CERILLA;
	
	static Ingrediente randomIng() {
		int r = (int) (Math.random()*Ingrediente.values().length - 1);
		Ingrediente[] pick = Ingrediente.values();
		return pick[r];
	}
	
}

package fp.dam.psp.EvPrimera.TEMA2.OCTUBRE.Dia21.Fumadores.OtraVersion2.Lock;
public enum Ingrediente {

	TABACO, CERILLAS, PAPEL;
	
	public static Ingrediente get() {
		return values()[(int)(Math.random()*1000)%values().length];
	}

}

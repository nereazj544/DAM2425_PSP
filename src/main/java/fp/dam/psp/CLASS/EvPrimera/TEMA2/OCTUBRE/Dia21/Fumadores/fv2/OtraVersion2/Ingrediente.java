package fp.dam.psp.CLASS.EvPrimera.TEMA2.OCTUBRE.Dia21.Fumadores.fv2.OtraVersion2;

public enum Ingrediente {

	TABACO, CERILLAS, PAPEL;
	
	public static Ingrediente get() {
		return values()[(int)(Math.random()*1000)%values().length];
	}

}

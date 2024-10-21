package fp.dam.psp.EvPrimera.TEMA2.OCTUBRE.Dia21.FumadoresConIntefazGafica;


public enum Ingredientes {
    TABACO, PAPEL, CERILLAS;
    public static Ingredientes get() {
		return values()[(int)(Math.random()*1000)%values().length];
	}
}

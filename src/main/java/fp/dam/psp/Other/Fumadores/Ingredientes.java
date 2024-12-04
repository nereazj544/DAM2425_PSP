package fp.dam.psp.Other.Fumadores;


public enum Ingredientes {
    TABACO, PAPEL, CERILLAS;
    public static Ingredientes get() {
		return values()[(int)(Math.random()*1000)%values().length];
	}
}

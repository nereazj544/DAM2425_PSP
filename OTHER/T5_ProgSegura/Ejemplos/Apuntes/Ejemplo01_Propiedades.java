package T5_ProgSegura.Ejemplos.Apuntes;

public class Ejemplo01_Propiedades {
	public static void main(String[] args) {
		String t[] = { "java.class.path", "java.home", "java.vendor", "java.version", "os.name", "os.version",
				"user.dir", "user.home", "user.name" };
		System.out.println("PROPIEDAD VALOR");
		for (int i = 0; i < t.length; i++)
			System.out.format("%-20s ==> %s\n", t[i], System.getProperty(t[i]));
	}
}

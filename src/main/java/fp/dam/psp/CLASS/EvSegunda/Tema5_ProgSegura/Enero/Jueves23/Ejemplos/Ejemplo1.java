package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Enero.Jueves23.Ejemplos;

public class Ejemplo1 {
    public static void main(String[] args) {
        String[] t = { "java.class.path", "java.home", "java.vendor", "java.version",
                "os.name", "os.version", "user.dir", "user.home", "user.name" };

        System.out.println("PROPIEDAD               VALOR");
        for (int i = 0; i < t.length; i++) {
            System.out.format("%-20s ==> %s\n", t[i], System.getProperty(t[i]));


    }
}
}

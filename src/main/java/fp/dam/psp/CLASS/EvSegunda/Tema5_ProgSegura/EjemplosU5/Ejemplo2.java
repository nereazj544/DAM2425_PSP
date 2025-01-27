package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.EjemplosU5;

import java.security.*;

public class Ejemplo2 {
    public static void main(String[] args) {
        String t[] = { "java.class.path", "java.home", "java.vendor", "java.version",
                "os.name", "os.version", "user.dir", "user.home", "user.name" };
        System.setSecurityManager(new SecurityManager()); //! Esta obsleto
        System.out.println("PROPIEDAD VALOR");
        for (int i = 0; i < t.length; i++)
            try {
                System.out.format("%-20s ==> %s\n", t[i], System.getProperty(t[i]));
            } catch (AccessControlException e) {
                System.out.println(e.getLocalizedMessage());
            }
    }
}
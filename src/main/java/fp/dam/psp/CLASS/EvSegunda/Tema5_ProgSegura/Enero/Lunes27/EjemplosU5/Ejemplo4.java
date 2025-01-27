package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Enero.Lunes27.EjemplosU5;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Ejemplo4 {
    public static void main(String[] args)  throws NoSuchAlgorithmException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-256");
         md.update("> un mesaje de prueba ".getBytes(StandardCharsets.UTF_8));

         byte [] rs = md.digest();
        System.out.println("\n ==========\n");
        System.out.println("Algoritmo: " + md.getAlgorithm());
        System.out.println("Proveedor: " + md.getProvider().toString());
        System.out.println("Longitud del resumen: " + md.getDigestLength());
        System.out.println("Resumen en Hexadecimal: " + hexadecimal(rs));
        System.out.println("Resumen en Base64: " +  Base64.getEncoder().encodeToString(rs));
    }

    private static String hexadecimal(byte[] rs) {
    StringBuilder hex = new StringBuilder();
        for (int i = 0; i < rs.length; i++)
            hex.append(String.format("%02X", rs[i]));
        return hex.toString();
    }
}

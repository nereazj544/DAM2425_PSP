package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.ActividadesU5PDF.Actividad2;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;

public class VerificarHash {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("> NO SE HA INTRODUCIDO UNA RUTA DEL FICHERO Y EL SHA. \n> REINICIE EL PROGRAMA");
            System.exit(0);
        }

        try {

            String cn = new String(Files.readAllBytes(Paths.get(args[0])));

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(cn.getBytes());

            StringBuilder sb = new StringBuilder();

            for (byte b : hash) {
                String h = Integer.toHexString(0xff & b);
                if (h.length() == 1)
                    sb.append('0');
                sb.append(h);

            }

            String rAl = new String(Files.readAllBytes(Paths.get(args[1]))).trim();

            if (sb.toString().equals(rAl)) {
                System.out.println("\n> NO HA SIDO MODIFICADO");
            } else {
                System.out.println("\n> EL FICHERO HA SIDO MODIFICADO");
            }

        } catch (Exception e) {
            System.out.println("> Se ha producido un error");
            System.out.println("> Más informacion: " + e.getMessage());
        }

        // ! END MAIN
    }
}

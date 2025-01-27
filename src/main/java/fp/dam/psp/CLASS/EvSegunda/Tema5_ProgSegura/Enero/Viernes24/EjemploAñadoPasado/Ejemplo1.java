package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Enero.Viernes24.EjemploAñadoPasado;

import java.io.*;
import java.security.*;


public class Ejemplo1 {
    public static void main(String[] args) throws Exception {
        KeyStore ks = KeyStore.getInstance("pkcs12");

        char[] pwdArray = "renn".toCharArray();

        ks.load(new FileInputStream("C:\\cygwin64\\home\\nzjha\\certs\\keystore.p12"), pwdArray);

        PrivateKey priv = (PrivateKey) ks.getKey("neo", pwdArray);

        try (BufferedInputStream in = new BufferedInputStream(
                new FileInputStream("C:\\Users\\nzjha\\Desktop\\Clase-eciplse\\ECIPLSE\\PSP\\pdf\\Colecciones.pdf"));
             BufferedOutputStream out = new BufferedOutputStream(
                     new FileOutputStream(System.getProperty("user.home") + "//Desktop//Colecciones.pdf.sing"))) {

            Signature sing = Signature.getInstance("SHA512withRSA");
            sing.initSign(priv);

            byte[] buffer = new byte[1024];

            int n;

            while ((n = in.read(buffer)) > 0) {
                sing.update(buffer, 0, n);
            }
            byte[] signature = sing.sign();
            out.write(signature);

        }
    }
}

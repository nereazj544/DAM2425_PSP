package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Viernes24.EjemploAñadoPasado;

import java.io.*;
import java.security.*;

public class Ejemplo2 {
    //Verificar Firma
    public static void main(String[] args) throws Exception {
        KeyStore ks = KeyStore.getInstance("pkcs12");
        char [] password = "renn".toCharArray();
        ks.load(new FileInputStream("[ruta]"), password);
        PublicKey pubKey = (PublicKey) ks.getCertificate("neo").getPublicKey();

        try (
                BufferedInputStream inF = new BufferedInputStream(
                        Ejemplo2.class.getResourceAsStream("[ruta]"));
                BufferedInputStream inS = new BufferedInputStream(
                        new FileInputStream(System.getProperty("user.home") + "[ruta]"))
        ) {
            Signature sign = Signature.getInstance("SHA512withRSA");
            sign.initVerify(pubKey);
            byte [] buffer = new byte[1024];
            int n;
            while ((n = inF.read(buffer)) > 0)
                sign.update(buffer, 0, n);
            System.out.println(sign.verify(inS.readAllBytes()));
        }
    }

}

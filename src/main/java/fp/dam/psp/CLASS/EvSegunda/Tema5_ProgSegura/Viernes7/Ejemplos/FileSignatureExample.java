package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Viernes7.Ejemplos;

import java.io.*;
import java.security.*;

public class FileSignatureExample {
    public static byte[] signFile(PrivateKey privateKey, String fileName) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);

        FileInputStream fis = new FileInputStream(fileName);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            signature.update(buffer, 0, len);
        }
        fis.close();

        return signature.sign();
    }

    public static boolean verifyFile(PublicKey publicKey, String fileName, byte[] signatureBytes)
            throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);

        FileInputStream fis = new FileInputStream(fileName);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            signature.update(buffer, 0, len);
        }
        fis.close();

        return signature.verify(signatureBytes);
    }
}
package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Febrero.Viernes7.Ejemplos;

import java.security.*;

public class SignatureExample {
    public static void main(String[] args) throws Exception {
        // Generar par de claves
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();

        // Crear firma
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(pair.getPrivate());

        // Datos a firmar
        byte[] message = "Mensaje a firmar".getBytes();
        signature.update(message);

        // Generar firma
        byte[] signatureBytes = signature.sign();

        // Verificar firma
        signature.initVerify(pair.getPublic());
        signature.update(message);
        boolean verified = signature.verify(signatureBytes);

        System.out.println("Firma verificada: " + verified);
    }
}
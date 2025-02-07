package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Febrero.Viernes7.EjemploPDF;

import java.security.*;

public class Ejemplo5 {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        keyGen.initialize(1024, random);
        KeyPair par = keyGen.generateKeyPair();
        PrivateKey privada = par.getPrivate();
        PublicKey publica = par.getPublic();
        Signature signature = Signature.getInstance("SHA256withDSA");

        // FIRMA

        // fase 1: inicialización
        signature.initSign(privada, random);

        // fase 2: actualización
        signature.update("texto de prueba".getBytes());

        // fase 3: firma
        byte [] firma = signature.sign();

        // VERIFICACIÓN

        // fase 1: inicialización
        signature.initVerify(publica);

        // fase 2: actualización
        signature.update("texto de prueba".getBytes());

        // fase 3: verificación
        System.out.println(signature.verify(firma) ? "VERIFICADO" : "NO VERIFICADO");
    }
}

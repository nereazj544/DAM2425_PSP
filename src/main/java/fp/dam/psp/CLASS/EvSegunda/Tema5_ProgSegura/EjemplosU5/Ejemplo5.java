package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.EjemplosU5;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

public class Ejemplo5 {
    public static void main(String[] args) throws NoSuchAlgorithmException{
        KeyPairGenerator keyPG = KeyPairGenerator.getInstance("DSA");
        SecureRandom sRandom = SecureRandom.getInstance("SHA1PRING");

        keyPG.initialize(1024, sRandom);
        KeyPair par = keyPG.genKeyPair();
        PrivateKey prv = par.getPrivate();
        PublicKey pub = par.getPublic();


    }
    
}

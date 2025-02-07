package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Febrero.Viernes7.TAREADELSERVIDORSIGNAURE.V2;

import java.net.*;
import java.security.*;
import java.time.*;

public class TaskSS implements Runnable {
    private KeyPair keyPair;
    private  final Socket sck;

    public TaskSS(Socket sck) {
        this.sck = sck;
    }

    private void error(String s) {
        System.err.printf("ERROR: %s: %s\n", LocalTime.now(), s);
    }

    public  void firmar(){
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
            SecureRandom sRandom = SecureRandom.getInstance("SHA1PRNG");
            keyGen.initialize(1024, sRandom);

            keyPair = keyGen.generateKeyPair();
            PrivateKey priv = keyPair.getPrivate();
            PublicKey pub = keyPair.getPublic();

            Signature sg = Signature.getInstance("SHA256withDSA");
        } catch (NoSuchAlgorithmException e) {
            error(e.getLocalizedMessage());
        }

    }

    @Override
    public void run() {
    try (sck){

    } catch (Exception e) {
        error(e.getLocalizedMessage());
    }
    }
}

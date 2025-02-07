package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Febrero.Viernes7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.*;
import java.time.LocalTime;

public class TaskServerSigna implements Runnable {
    private  final Socket sck;
    public TaskServerSigna(Socket sck) {
    this.sck = sck;
    }


    private void error(String s) {
        System.err.printf("ERROR: %s: %s\n", LocalTime.now(), s);
    }


    @Override
    public void run() {
try (sck){

    // Cifrado tengo sueño :^
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
    SecureRandom Sr = SecureRandom.getInstance("SHA1PRNG");
    keyGen.initialize(1024, Sr);

    KeyPair keyPair = keyGen.generateKeyPair();
    PrivateKey privateKey = keyPair.getPrivate();
    PublicKey publicKey = keyPair.getPublic();
    Signature sn = Signature.getInstance("SHA256withDSA");


    // todo HASH (?)
    DataInputStream in = new DataInputStream(sck.getInputStream());
    DataOutputStream out = new DataOutputStream(sck.getOutputStream());

    String rq = in.readUTF();

    if (rq.equals("hash")){
        MessageDigest md = MessageDigest.getInstance(in.readUTF());

        byte [] b = new byte[1024];
        int n;

        while ((n = in.read(b)) != 1)
            md.update(b, 0, n);

byte [] hash = md.digest();



    }
    } catch (NoSuchAlgorithmException e) {
    error(e.getLocalizedMessage());
} catch (IOException e) {
    error(e.getLocalizedMessage());
}


    }

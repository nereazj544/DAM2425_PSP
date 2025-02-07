package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Febrero.Viernes7.TAREADELSERVIDORSIGNAURE.V2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.security.*;
import java.time.*;

public class TaskSS implements Runnable {
    private final KeyPair keyPair;
    private final Socket sck;

    public TaskSS(Socket sck, KeyPair keyPair) {
        this.sck = sck;
        this.keyPair = keyPair;
    }

    private void error(String s) {
        System.err.printf("ERROR: %s: %s\n", LocalTime.now(), s);
    }

    /*
     * public void firmar() {
     * try {
     * KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
     * SecureRandom sRandom = SecureRandom.getInstance("SHA1PRNG");
     * keyGen.initialize(1024, sRandom);
     * 
     * keyPair = keyGen.generateKeyPair();
     * PrivateKey priv = keyPair.getPrivate();
     * PublicKey pub = keyPair.getPublic();
     * 
     * Signature sg = Signature.getInstance("SHA256withDSA");
     * } catch (NoSuchAlgorithmException e) {
     * error(e.getLocalizedMessage());
     * }
     * }
     */

    @Override
    public void run() {
        try (sck;
                DataInputStream in = new DataInputStream(sck.getInputStream());
                DataOutputStream out = new DataOutputStream(sck.getOutputStream())) {

            String m = in.readUTF();
            System.out.println("Mensaje recibido: " + m);

            // TODO == FIRMA ==
            Signature sg = Signature.getInstance("SHA256withDSA");
            sg.initSign(keyPair.getPrivate());

            sg.update(m.getBytes()); // ? Actualiza los datos

            byte[] fr = sg.sign(); // ? Firma los datos

            out.writeUTF(m);
            out.write(fr);

            sg.initVerify(keyPair.getPublic());
            sg.update(m.getBytes());
            boolean v = sg.verify(fr);
            out.writeBoolean(v);
            System.out.println("Firma verificada: " + v);

            // if (sg.verify(fr)) {
            //     System.out.println("Firma verificada");
            // } else {
            //     System.out.println("Firma no verificada");
            // }

        } catch (Exception e) {
            error(e.getLocalizedMessage());
        }
    }
}

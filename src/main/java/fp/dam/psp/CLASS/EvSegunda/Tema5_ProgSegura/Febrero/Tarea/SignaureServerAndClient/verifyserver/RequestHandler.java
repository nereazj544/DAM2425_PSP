package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Febrero.Tarea.SignaureServerAndClient.verifyserver;

import java.io.*;
import java.net.*;
import java.security.*;
import java.security.cert.*;
import java.time.*;
import java.util.*;

//import fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Febrero.Viernes7.TAREADELSERVIDORSIGNAURE.V1.signatureServer;

public class RequestHandler implements Runnable {

    private final KeyPair keyPair;
    private final Socket sck;

    private void error(String s) {
        System.err.printf("ERROR: %s: %s\n", LocalTime.now(), s);
    }

    public RequestHandler(Socket sck, KeyPair kPair) {
        // TODO Auto-generated constructor stub
        this.sck = sck;
        this.keyPair = kPair;
    }

    @Override
    public void run() {
        try (sck;
                DataInputStream in = new DataInputStream(sck.getInputStream());
                DataOutputStream out = new DataOutputStream(sck.getOutputStream())) {

            String sg = in.readUTF();
            String[] parts = sg.split("#");
            if (parts.length != 3) {
                out.writeUTF("FORMATO DE FIRMA INCORRECTO");
                return;
            }

            byte[] sgBytes = Base64.getDecoder().decode(parts[0]);
            String algo = parts[1];
            byte[] cerBytes = Base64.getDecoder().decode(parts[1]);




            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cer = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(cerBytes));

            int file = in.readInt();
            byte[] fileBytes = new byte[file];
            in.readFully(fileBytes);

            Signature sgn = Signature.getInstance(algo);
            sgn.initVerify(cer.getPublicKey());
            sgn.update(fileBytes);

            boolean v = sgn.verify(fileBytes);
            if (v) {
                out.writeUTF("FIRMA CORRECTA");
            } else {
                out.writeUTF("FIRMA INCORRECTA");
            }
            

        } catch (Exception e) {
            error(e.getLocalizedMessage());
        }

    }
}

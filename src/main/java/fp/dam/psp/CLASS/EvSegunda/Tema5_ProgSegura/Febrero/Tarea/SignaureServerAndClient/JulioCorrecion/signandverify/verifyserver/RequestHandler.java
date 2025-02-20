package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Febrero.Tarea.SignaureServerAndClient.JulioCorrecion.signandverify.verifyserver;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.time.LocalTime;
import java.util.Base64;

public class RequestHandler implements  Runnable{
    private  final Socket sck;
    private   KeyStore keyStore;
    private  KeyPair keyPair;
    public RequestHandler(Socket sck) {
        this.sck = sck;

    }


    private void error(String s) {
        System.err.printf("ERROR: %s: %s\n", LocalTime.now(), s);
    }

    @Override
    public void run() {
        try (sck;
             DataInputStream in = new DataInputStream(sck.getInputStream());
             DataOutputStream out = new DataOutputStream(sck.getOutputStream())){

            String [] DataSign = in.readUTF().split("#");
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] fileSign = decoder.decode(DataSign[0]);

            String algoHash = DataSign[1];
            byte[] cerEncode = decoder.decode(DataSign[2]);

            CertificateFactory cf  = CertificateFactory.getInstance("X.509");
            X509Certificate cer = (X509Certificate)  cf.generateCertificate(new ByteArrayInputStream(cerEncode));




//            keyStore.load( "practicas".toCharArray());


        } catch (Exception e) {
            error(e.getLocalizedMessage());
        }
    }
}

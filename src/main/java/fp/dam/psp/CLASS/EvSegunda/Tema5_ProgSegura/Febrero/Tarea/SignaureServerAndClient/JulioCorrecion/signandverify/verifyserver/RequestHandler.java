package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Febrero.Tarea.SignaureServerAndClient.JulioCorrecion.signandverify.verifyserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyStore;
import java.time.LocalTime;

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

//            keyStore.load( "practicas".toCharArray());


        } catch (Exception e) {
            error(e.getLocalizedMessage());
        }
    }
}

package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Febrero.Tarea.SignaureServerAndClient.JulioCorrecion.signandverify.verifyserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private  static KeyPair kPair;
    private  static KeyStore kStore;
    public static void main(String[] args) {
        try (ServerSocket sSck = new ServerSocket(6000)) {
//            KeyPairGenerator kPairGen = KeyPairGenerator.getInstance("PKCS12");
//            kPairGen.initialize(1024);

//            kPair = kPairGen.generateKeyPair();

            ExecutorService ExSer = Executors.newFixedThreadPool(100);
            System.out.println(" ");
            System.out.println(". . . Servidor iniciando By Nerea ZJ :) . . . ");
            System.out.println(". . . Puerto en escucha: 6000 . . . ");

            while (true){
                Socket sck = sSck.accept();
                sck.setSoTimeout(6000);
                ExSer.execute(new RequestHandler(sck));
            }

        } catch (Exception e) {
            e.getMessage();
        }
    }
}

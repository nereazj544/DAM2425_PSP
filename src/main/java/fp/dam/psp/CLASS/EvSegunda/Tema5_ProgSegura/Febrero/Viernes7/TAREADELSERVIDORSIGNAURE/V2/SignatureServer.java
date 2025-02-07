package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Febrero.Viernes7.TAREADELSERVIDORSIGNAURE.V2;

//! IMPORTES

import java.net.*;
import java.util.*;
import java.security.*;
import java.util.concurrent.*;

public class SignatureServer {

    private static KeyPair kPair;

    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(6000)) {
            KeyPairGenerator kGen = KeyPairGenerator.getInstance("DSA");
            kGen.initialize(1024);
            kPair = kGen.generateKeyPair();

            ExecutorService ExSer = Executors.newFixedThreadPool(100);
            System.out.println(" ");
            System.out.println(". . . Servidor iniciando . . . ");
            System.out.println(". . . Puerto en escucha: 6000 . . . ");


            while (true){
                Socket sck = s.accept();
                sck.setSoTimeout(6000);
                ExSer.execute(new TaskSS(sck, kPair));

            }

        }catch (Exception e){
            e.getMessage();
        }
    }

}

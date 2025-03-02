package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ServidorAgenda.Server;

import java.io.*;
import java.net.*;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.concurrent.*;
import javax.net.ssl.*;

//TODO MIRAR PORQUE NO CARRULA

public class Server {

    // CLAVES
    // private static final String KY_Pass = "server";
    // private static final String KY_Arch =
    // "src\\main\\java\\fp\\dam\\psp\\CLASS\\EvSegunda\\ActividadesdeEntrega\\ServidorAgenda\\Key\\Servidor.p12";

    // private static PrivateKey privateKey;
    // private static X509Certificate cert;

    private static KeyPair keyPair;
    
    
    public static void main(String[] args) {
        try (ServerSocket sSck = new ServerSocket(6000)) {
            
            // Agenda.Claves();
            // cargarClaves();
            
            KeyPairGenerator KY_gen = KeyPairGenerator.getInstance("RSA");
            KY_gen.initialize(2048);
            keyPair = KY_gen.generateKeyPair();
            
            ExecutorService ExSer = Executors.newFixedThreadPool(100);
            System.out.println(" ");
            System.out.println(". . . Servidor iniciando . . . ");
            System.out.println(". . . Puerto en escucha: 6000 . . . ");
            
            while (true) {
                Socket sck = sSck.accept();
                Agenda agenda = new Agenda(sck);
                // ExSer.execute(new Agenda(sck));
                // KeyTask KY_Task = new KeyTask(sck, keyPair);
            }

        } catch (Exception e) {
            e.getMessage();
        }
    }

}

package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ServidorAgenda_1.Server;

import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    // TODO CLAVES
    private static final String KY_Pass = "server"; // ! Contraseña

    // ! Ruta de la clave
    private static final String KY_Path = "src\\main\\java\\fp\\dam\\psp\\CLASS\\EvSegunda\\ActividadesdeEntrega\\ServidorAgenda_1\\Key\\Servidor.p12";

    public static void main(String[] args) {
        try (ServerSocket SV_Sck = new ServerSocket(6000)) {
            //TODO_ Servidor 1
            ExecutorService Ex_Serv = Executors.newFixedThreadPool(100);
            
            System.out.println(". . . . . . . . . . . . . . . . . . . . . . . . ");
            System.out.println("> . . . SERVIDOR INICIADO . . . ");
            System.out.println("> . . . Puerto en escucha: 6000 . . . ");
            System.out.println(". . . . . . . . . . . . . . . . . . . . . . . . ");
            
            
            //TODO_ KEY
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(new FileInputStream(KY_Path), KY_Pass.toCharArray());
            PrivateKey KY_privSV = (PrivateKey) ks.getKey("servidor", KY_Pass.toCharArray());
            
            Certificate cert = ks.getCertificate("servidor");
            PublicKey KY_pubSV = cert.getPublicKey();
            
            //TODO_ Servidor 2
            while (true) {
                Socket sck = SV_Sck.accept();
                System.out.println("> Cliente conectado: " + sck.getInetAddress().getHostAddress());

                Ex_Serv.submit(new AgendaTask (sck, KY_privSV, KY_pubSV));

            }

            
        } catch (Exception e) {
            System.out.println("> " + e.getMessage());
        }
    }

}

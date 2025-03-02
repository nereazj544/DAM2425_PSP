package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ServidorAgenda_1.Server;

import java.net.ServerSocket;
import java.net.Socket;
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
            
            
            
            //TODO_ Servidor 2
            while (true) {
                Socket sck = SV_Sck.accept();
                
            }

            
        } catch (Exception e) {
            System.out.println("> " + e.getMessage());
        }
    }

}

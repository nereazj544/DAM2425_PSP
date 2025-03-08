package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ServidorAgenda_V1.server;

import java.net.ServerSocket;
import java.net.Socket;
//! --- IMPORTS ---
import java.security.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//! --- END IMPORTS ---

public class Server {
    public static void main(String[] args) {
        try (ServerSocket SV_sck = new ServerSocket(6000)) {
            ExecutorService Ex_Serv = Executors.newFixedThreadPool(100);

            System.out.println(". . . . . . . . . . . . . . . . . . . . . . . . ");
            System.out.println("> . . . SERVIDOR INICIADO . . . ");
            System.out.println("> . . . Puerto en escucha: 6000 . . . ");
            System.out.println(". . . . . . . . . . . . . . . . . . . . . . . . ");

            while (true) {
                Socket sck = SV_sck.accept();
                System.out.println("> Cliente conectado: " + sck.getInetAddress().getHostAddress());
                Ex_Serv.execute(new AgendaTask(sck));
            }


        } catch (Exception e) {
            System.out.println("> " + e.getMessage());
        }
    }
}

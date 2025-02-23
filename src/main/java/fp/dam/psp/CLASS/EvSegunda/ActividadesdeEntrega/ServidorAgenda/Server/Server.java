package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ServidorAgenda.Server;

import java.net.*;
import java.util.concurrent.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket sSck = new ServerSocket(6000)) {

            Agenda.Claves();

            ExecutorService ExSer = Executors.newFixedThreadPool(100);
            System.out.println(" ");
            System.out.println(". . . Servidor iniciando By Nerea ZJ :) . . . ");
            System.out.println(". . . Puerto en escucha: 6000 . . . ");

            while (true) {
                Socket sck = sSck.accept();
                // sck.setSoTimeout(6000);
                ExSer.execute(new Agenda(sck));
            }

        } catch (Exception e) {
            e.getMessage();
        }
    }
}

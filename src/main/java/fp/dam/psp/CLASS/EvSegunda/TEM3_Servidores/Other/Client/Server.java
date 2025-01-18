package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.ActividadesdeEntrega.Client;

import java.net.*;
import java.util.concurrent.*;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket sSck = new ServerSocket(6000);
        ExecutorService exService = Executors.newFixedThreadPool(100);

        System.out.println(". . . Servidor iniciando . . . ");

        while (true) {
            Socket sck = sSck.accept();
            sck.setSoTimeout(10000);
            exService.execute(new RequestTask(sck));
        }

    }
}

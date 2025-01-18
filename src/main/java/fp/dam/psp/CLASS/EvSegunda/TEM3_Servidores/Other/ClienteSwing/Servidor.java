package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Other.ClienteSwing;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {
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

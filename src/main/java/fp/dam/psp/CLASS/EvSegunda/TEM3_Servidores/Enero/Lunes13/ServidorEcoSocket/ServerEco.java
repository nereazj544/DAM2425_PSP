package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Lunes13.ServidorEcoSocket;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerEco {

    public static void main(String[] args) throws Exception{
        ServerSocket sSocket = new ServerSocket(6000);
        ExecutorService service = Executors.newFixedThreadPool(100);
        System.out.println(" . . .  servidor activo . . .");

        while (true){
            Socket sck = sSocket.accept();

            service.submit(new TareaServidor(sck));
        }
    }
}

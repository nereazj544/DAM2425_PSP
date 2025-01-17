package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Viernes17;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;


public class Servidor {
    public static <ExcecutorService> void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        System.out.println("Servidor ECO escuchando en puerto 9000");
        while (true) {
            Socket socket = serverSocket.accept();
            // crear un hilo pasándole el socket que atienda la petición
            socket.setSoTimeout(5000);
            //? Si se tarda en enviar algo se va a la puta
            executorService.submit(new RequestTask(socket));
        }
    }
}

package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Viernes17.v2;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {

    public static <ExcecutorService> void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        System.out.println("Servidor ECO escuchando en puerto 9000");
        while (true) {
            Socket socket = serverSocket.accept();
            // crear un hilo pasándole el socket que atienda la petición
            socket.setSoTimeout(5000);
            executorService.submit(new RequestTask(socket));
        }

    }

}
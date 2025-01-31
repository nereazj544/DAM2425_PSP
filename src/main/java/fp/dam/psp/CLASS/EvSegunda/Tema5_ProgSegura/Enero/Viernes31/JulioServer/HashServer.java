package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Enero.Viernes31.JulioServer;
import java.io.*;
import  java.util.*;
import java.net.*;
import java.util.concurrent.*;

public class HashServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9000);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        System.out.println("Servidor ECO escuchando en puerto 9000");
        while (true) {
            Socket socket = server.accept();
            socket.setSoTimeout(5000);
            executorService.submit(new RequestHandler(socket));
        }
    }
}

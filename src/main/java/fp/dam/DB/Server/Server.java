package fp.dam.DB.Server;

import java.net.*;

import java.util.concurrent.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket sk = new ServerSocket(600)) {
            ExecutorService executorService = Executors.newFixedThreadPool(100);

            System.out.println(". . . Servidor iniciando . . . ");
            System.out.println("> Servidor conectado a MySql");
            System.out.println(" ");
            System.out.println("> Servidor ECO escuchando en puerto 600");

            while (true) {
                Socket sck = sk.accept();
                // sck.setSoTimeout(6000);
                executorService.execute(new ConexionSql(sck));
            }

        } catch (Exception e) {
            System.out.println("> Error: " + e.getMessage());
        }
    }

}

package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Enero.Viernes31;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(6000)){
            ExecutorService exS = Executors.newFixedThreadPool(100);
            System.out.println(". . . Servidor iniciando . . . ");

            while (true){
                Socket sck = s.accept();
                sck.setSoTimeout(6000);
                exS.execute(new RTask(sck));
            }
        } catch (Exception e) {
            System.out.println("Se ha detectado un error: " + e.getMessage());
        }
    }

}

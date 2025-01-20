package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ClienteConSwing.V2_ClienteText;

//! Import
import java.net.*;
import java.util.concurrent.*;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket sScok = new ServerSocket(6000);
            ExecutorService exSer = Executors.newFixedThreadPool(100);

            System.out.println(". . . Servidor iniciando . . . ");
            while (true) {
                Socket sck = sScok.accept();
                sck.setSoTimeout(60000);
                exSer.execute(new RequestTask(sck));
            }
    
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

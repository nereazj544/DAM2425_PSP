package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Febrero.Viernes7.TAREADELSERVIDORSIGNAURE.V2;

//! IMPORTES

import java.net.*;
import java.util.*;
import java.security.*;
import java.util.concurrent.*;

public class SignatureServer {


    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(6000)) {

            ExecutorService ExSer = Executors.newFixedThreadPool(100);
            System.out.println(" ");
            System.out.println(". . . Servidor iniciando . . . ");
            System.out.println(". . . Puerto en escucha: 6000 . . . ");


            while (true){
                Socket sck = s.accept();
                sck.setSoTimeout(6000);
                ExSer.execute(new TaskSS(sck));

            }

        }catch (Exception e){
            e.getMessage();
        }
    }

}

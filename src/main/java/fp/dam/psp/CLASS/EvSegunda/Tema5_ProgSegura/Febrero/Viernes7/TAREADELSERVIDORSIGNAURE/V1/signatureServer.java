package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Febrero.Viernes7.TAREADELSERVIDORSIGNAURE.V1;

/*
! FUNCION:
* Se usara Signature, convina: MessageDifest y Cipher
Firmar = see. bytes
1 obtener el hash de la ser
2 cifrar el hash obtenido
! Nos importa un pimiento este proceso :) by profe

se hace la firma y la verificacion
 */


import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class signatureServer {
    public static void main(String[] args) {
        try(ServerSocket s = new ServerSocket(6000)){

            ExecutorService ExSer = Executors.newFixedThreadPool(100);
            System.out.println(" ");
            System.out.println(". . . Servidor iniciando . . . ");
            System.out.println(". . . Puerto en escucha: 6000 . . . ");

            while (true){
                Socket sck = s.accept();
                sck.setSoTimeout(6000);
                ExSer.execute(new TaskServerSigna(sck));
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

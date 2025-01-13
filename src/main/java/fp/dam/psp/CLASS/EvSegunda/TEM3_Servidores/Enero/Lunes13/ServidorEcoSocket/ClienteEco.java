package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Lunes13.ServidorEcoSocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteEco {
    public static void main(String[] args) throws IOException {
       final Socket socket = new Socket("localhost", 6000);
        try(socket){
            new DataOutputStream(socket.getOutputStream()).writeUTF("k"); //? k representa la peticion
            String s = new DataInputStream(socket.getInputStream()).readUTF(); //? respuesta que se muestra
    System.out.println(s);
        }catch (Exception e){

        }
    }
}

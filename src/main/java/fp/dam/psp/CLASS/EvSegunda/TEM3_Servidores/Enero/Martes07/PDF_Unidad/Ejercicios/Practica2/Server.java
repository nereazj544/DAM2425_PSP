package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Unidad.Ejercicios.Practica2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try{

            ServerSocket s = new ServerSocket(6000);
            System.out.println(". . . Servidor iniciando . . . ");
            
            Socket cliente = s.accept();

            DataInputStream in = new DataInputStream(cliente.getInputStream());
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream()); 

            String m = in.readUTF();
            System.out.println("> Cliente al habla: " + m);

            out.writeUTF("> Mensaje recibido");

            cliente.close();
            s.close();


        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Lunes13.ServidorEcoSocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TareaServidor implements  Runnable{
    private final  Socket socket;

    public TareaServidor(Socket sck) {
        this.socket = sck;
    }

    //! Cerrandose aqui no se cierra en el cliente, le va a llegar un EOF (traduccion: error)
    @Override
    public void run() {
       try(socket){
           String s = new DataInputStream(socket.getInputStream()).readUTF();
           System.out.println(" Peticion " + s + " :" + socket.getInetAddress() + " : " + socket.getPort());
           new DataOutputStream(socket.getOutputStream()).writeUTF(s);

       } catch (Exception e) {


       }
    }
}

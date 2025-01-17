package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Viernes17;


import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

public class RequestTask implements Runnable {

    private final Socket socket;

    public RequestTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (socket) {


            String s;
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            s = new DataInputStream(socket.getInputStream()).readUTF();
            System.out.println("Petición : " + s + " : " +
                    socket.getInetAddress() + " : " + socket.getPort() +
                    " : " + LocalDateTime.now());
try {

    String l;
    l = in.readUTF();
    for (;;) {
        out.writeUTF(in.readUTF());
        System.out.println("> Mensaje recibido: " + l);
        out.writeUTF(l);
    }
} catch (EOFException e) {
    System.out.println(">ERROR");
}


//            new DataOutputStream(socket.getOutputStream()).writeUTF(s);
//            System.out.println("respuesta : " + s + " : " +
//                    socket.getLocalAddress() + " : " + socket.getLocalPort() +
//                    " : " + LocalDateTime.now());



        } catch (Exception e) {
            System.out.println("> ERRROR " + e.getMessage());

        }
    }
}

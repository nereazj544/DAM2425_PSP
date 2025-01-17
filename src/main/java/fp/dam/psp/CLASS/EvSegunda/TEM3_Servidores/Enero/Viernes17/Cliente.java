package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Viernes17;

import java.io.*;
import java.net.*;

public class Cliente {

    public static void main(String[] args) throws IOException {
        final Socket socket = new Socket("localhost", 9000);
        try (socket) {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            for (int i = 0; i <10 ; i++) {
                out.writeUTF("hola" + i);
//                String s = new DataInputStream(socket.getInputStream()).readUTF();
//                System.out.println(s);
//                socket.shutdownOutput();

                for(;;);
            }

        }
    }
}

package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Viernes17.v2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        try (Socket socket = new Socket("localhost", 9000)) {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            for (int i = 0; i < 10; i++)
                out.writeUTF("hola " + i);
            socket.shutdownOutput();
            for (;;);
        }

    }
}
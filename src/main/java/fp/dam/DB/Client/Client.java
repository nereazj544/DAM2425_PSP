package fp.dam.DB.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (
                Socket s = new Socket("localhost", 600);
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                Scanner sc = new Scanner(System.in);) {

            while (true) {
                System.out.println("> Escribe la opcion deseada: ");
                System.out.print("> ");
                String cmd = sc.nextLine();
                out.writeUTF(cmd);

                System.out.println("> Respuesta: " + in.readUTF());
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

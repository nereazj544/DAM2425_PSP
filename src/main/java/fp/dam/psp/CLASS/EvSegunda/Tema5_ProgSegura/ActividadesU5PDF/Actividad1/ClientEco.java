package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.ActividadesU5PDF.Actividad1;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientEco {
    public static void main(String[] args) {
        try (Socket s = new Socket("localhost", 6000)) {
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            Scanner sc = new Scanner(System.in);
            String l;

            while (true) {
                System.out.print("> Introduce un mensaje: ");
                l = sc.nextLine().toLowerCase();
                out.writeUTF(l);

                if (l.equals("fin")) {
                    System.out.println("> Desconectando del servidor");
                    break;
                }

                l = in.readUTF();
                System.out.println("> Mensaje recibido: " + l);
            }

            sc.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

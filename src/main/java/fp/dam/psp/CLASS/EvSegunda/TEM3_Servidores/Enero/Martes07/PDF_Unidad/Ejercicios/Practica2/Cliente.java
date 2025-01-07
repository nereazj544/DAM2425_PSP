package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Unidad.Ejercicios.Practica2;

import java.net.*;
import java.io.*;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 6000);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            out.writeUTF("Hola servidor");
            String m = in.readUTF();
            System.out.println("> Servidor al habla: " + m);

            s.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

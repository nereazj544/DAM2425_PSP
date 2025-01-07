package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Unidad.Ejercicios.Practica3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try {

            Socket s = new Socket("localhost", 6000);

            DataInputStream in = new DataInputStream(s.getInputStream());

            String m = in.readUTF();
            System.out.println("> Mensjae del servidor: " + m);

            in.close();
            s.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

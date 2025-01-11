package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.ServidorEco.V2_Data;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEco_DataInput {

    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(6000);
            System.out.println(". . . Servidor iniciando . . . ");

            Socket sck = s.accept();

            // BufferedReader br = new BufferedReader(new InputStreamReader(sck.getInputStream()));

            DataInputStream in = new DataInputStream(sck.getInputStream());
            PrintWriter pw = new PrintWriter(sck.getOutputStream(), true);

            String l;

            while ((l = in.readUTF()) != null) {
                System.out.println("> Recibido: " + l);
                pw.println(l);
                if (l.equals("fin")) {
                    break;
                }
            }

            pw.close();
            in.close();
            s.close();
            sck.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
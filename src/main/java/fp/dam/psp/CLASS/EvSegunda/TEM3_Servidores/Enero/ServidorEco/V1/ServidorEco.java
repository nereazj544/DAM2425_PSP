package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.ServidorEco.V1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEco {
    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(6000);
            System.out.println(". . . Servidor iniciando . . . ");

            Socket sck = s.accept();

            BufferedReader br = new BufferedReader(new InputStreamReader(sck.getInputStream()));

            PrintWriter pw = new PrintWriter(sck.getOutputStream(), true);

            String l;

            while ((l = br.readLine()) != null) {
                System.out.println("> Recibido: " + l);
                pw.println(l);
                if (l.equals("fin")) {
                    break;
                }
            }

            br.close();
            pw.close();
            s.close();
            sck.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

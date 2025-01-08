package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Ejercios.Ej3_ServidorEco;

import java.io.*;
import java.net.*;

public class ClienteEco {
    public static void main(String[] args) {
        try {
            System.out.println("> Cliente iniciando . . . ");
            System.out.print("> Escriba: ");
            Socket s = new Socket("localhost", 6000);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader brs = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

            String l;

            while ((l = br.readLine()) != null) {
                pw.println(l);
                System.out.println("> Recibido: " + brs.readLine());
                if (l.equals("fin")) {
                    break;
                }
            }

            br.close();
            brs.close();
            pw.close();
            s.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

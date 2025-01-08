package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Ejercios.Ej2_ServidorHora;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(6000);
            System.out.println(". . . Servidor iniciando . . . ");

            while (true) {
                Socket sck = s.accept();

                DataOutputStream out = new DataOutputStream(sck.getOutputStream());

                LocalDateTime local = LocalDateTime.now();

                String hora = local.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                out.writeUTF(hora);
                System.out.println("> Hora enviada: " + hora);

                sck.close();
                out.close();

            }

        } catch (IOException e) {
            System.out.println("> Error: " + e.getMessage());
        }
    }
}

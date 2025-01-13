package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Lunes13.ServidorEcoSocketV2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Realizar: un servidor que con una peticion acepte varias cadenas y, 
un cliente que le envie varias cadenas. La respuesta del server a de ser lo mismo que pone el clinte.
 */

public class ServerEco {
    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(6000)) {
            System.out.println(". . . Servidor iniciando . . . ");

            while (true) {
                try (Socket sck = s.accept()) {
                    DataInputStream in = new DataInputStream(sck.getInputStream());
                    DataOutputStream out = new DataOutputStream(sck.getOutputStream());

                    System.out.println(
                            "> Cliente conectado desde " + sck.getInetAddress() + ", Puerto: " + sck.getPort() + " Hora actual: " + java.time.LocalTime.now());

                    String l;

                    while (true) {
                        l = in.readUTF();
                        if (l.equals("fin")) {
                            System.out.println("> Cliente desconectado");
                            break;
                        }

                        System.out.println("> Mensaje recibido: " + l);
                        out.writeUTF(l);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

        } catch (Exception e) {
            e.getMessage();
        }
        // TODO: handle exception
    }
}

package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Viernes10.ServidorEco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(6000);
            System.out.println(". . . Servidor iniciando . . . ");

            Socket sck = s.accept();

            BufferedReader br = new BufferedReader(new InputStreamReader(sck.getInputStream()));




        }catch (IOException e) {
            e.getMessage();
        }
    }
}

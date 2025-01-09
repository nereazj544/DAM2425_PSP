package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Ejercios.Ej6_Agenda.sin;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Servidor {

    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(6000);) {
            System.out.println(". . . Servidor iniciando . . . ");

            while (true) {
                Socket sck = s.accept();

                new Thread(new Mcliente(sck)).start();
            }

        } catch (IOException e) {
            System.out.println("> ERROR EN EL SERVIDOR");
            System.out.println("====================================");
            e.printStackTrace();
        }
    }
}

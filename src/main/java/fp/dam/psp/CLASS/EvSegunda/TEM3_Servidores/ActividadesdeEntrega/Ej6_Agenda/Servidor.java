package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.ActividadesdeEntrega.Ej6_Agenda;

import java.util.*;
import java.net.*;
import java.io.*;

public class Servidor {

    private static Map<String, Set<String>> contactos = Collections.synchronizedMap(new TreeMap<>());

    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(6000)) {
            System.out.println(". . . Servidor iniciando . . . ");

            while (true) {
                Socket clSck = s.accept();
                new Thread(new Contactos(clSck)).start();
            }

        } catch (Exception e) {
            System.out.println("> Error: " + e.getMessage());
        }
    }

}
package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.Ej6_Agenda;

// import java.io.BufferedReader;
import java.io.*;
import java.net.*;
import java.util.*;

public class Cliente {
    public static void main(String[] args) {
        try (Socket s = new Socket("localhost", 6000);
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                Scanner sc = new Scanner(System.in);) {
            System.out.println("> Cliente conectado al servidor");
            System.out.println("> Comandos disponibles:");
            System.out.println("- nombre: [nombre] [teléfono]");
            System.out.println("- buscar: [nombre]");
            System.out.println("- eliminar: [nombre]");
            System.out.println("- contactos");
            System.out.println("- salir");

            String l;
            while (true) {

                while (sc.hasNextLine()) {
                    l = sc.nextLine().toLowerCase();
                    if (l.equals("salir")) {
                        break;
                    }
                    out.writeUTF(l);
                    Respuesta(in);
                }
            }

        } catch (Exception e) {
            System.out.println("> Error: " + e.getMessage());
        }
    }

    private static void Respuesta(DataInputStream in) throws IOException {
        String r = in.readUTF();

        if (r.equals("OK")) {
            String dt = in.readUTF();
            if (!dt.isEmpty()) {
                System.out.println(dt);
            }
        } else if (r.startsWith("ERR")) {
            switch (r) {
                case "ERROR1":
                    System.out.println("> ERROR: Telefono ya existente");
                    break;
                case "ERROR2":
                    System.out.println("> ERROR: Contacto no encontrado");

                    break;
                case "ERROR3":
                    System.out.println("> ERROR: Sintaxis incorrecta");
                    System.out.println(in.readUTF());
                    break;

                default:
                    System.out.println("> ERROR DESCONCIDO. ");

            }
        }

    }
}

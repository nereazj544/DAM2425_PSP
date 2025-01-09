package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Ejercios.Ej6_Agenda.sin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {

        // ! Cuando se usa los try con recursos no hace falta poner close.
        try (Socket socket = new Socket("localhost", 6000);

                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

                BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner sc = new Scanner(System.in);) {

            System.out.println("> CLIENTE CONECTADO");
            System.out.println("> Opciones:");
            System.out.println("> 1. Añadir contacto");
            System.out.println("> 2. Buscar contacto");
            System.out.println("> 3. Eliminar contacto");
            System.out.println("> 4. Lista de contactos");
            System.out.println("> 5. Salir");

            while (true) {
                System.out.println("====================================");
                System.out.print("\n> Opción: ");

                int cmd = sc.nextInt();
                if (cmd == 5) {
                    System.out.println("> Cerrando cliente...");
                    break;
                }

                /* ? String convertido
                 * String k = String.valueOf(cmd);
                 * if (k.equals("5")) {
                 * System.out.println("> Cerrando cliente...");
                 * break;
                 * 
                 * }
                 */
                pw.println(cmd);

                String r = bf.readLine();

                if (r.equals("ok")) {
                    String l;
                    while (bf.ready() && (l = bf.readLine()) != null) {
                        System.out.println(l);
                    }
                    
                }else if (r.equals("ERROR1")) {
                    System.out.println("> Error: El teléfono ya existe");
                }else if(r.equals("ERROR2")) {
                    System.out.println("> Error: El contacto no existe/no encontrado");
                }else if(r.equals("ERROR3")) {
                    System.out.println("> Error: de sintaxis: ");
                    System.out.println(bf.readLine());
                    System.out.println(bf.readLine());
                }
            }

        } catch (IOException e) {
            System.out.println("> ERROR EN LA CONEXION CON EL SERVIDOR");
        }
    }
}

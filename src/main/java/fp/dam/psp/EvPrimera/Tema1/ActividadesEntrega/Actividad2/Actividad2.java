package fp.dam.psp.EvPrimera.Tema1.ActividadesEntrega.Actividad2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Crea un programa Java que use la clase ProcessBuilder
 *  para ejecutar el comando "cmd /C dir" y muestre en la consola lo siguiente:
 *  - Las variables de entorno del subproceso
 *  - Los argumentos usados para ejecutar el comando
 *  - La salida estandar del subproceso
 * 
 */

public class Actividad2 {
    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/C", "dir");

        System.out.println("\n ================================================================ \n");
        System.out.println("> Variables del entorno: ");
        System.out.println("\n ================================================================ \n");
        
        for (String v : System.getenv().keySet()) {
            System.out.println(v + ":  " + System.getenv(v));
        }
        
        
        
        System.out.println("\n ================================================================ \n");
        System.out.println("> Argumentos usados para ejecutar el comando: ");
        System.out.println("\n ================================================================ \n");
        System.out.println(String.join(" ", pb.command()));
        
        
        Process p = pb.start();
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        
        System.out.println("\n ================================================================ \n");
        System.out.println("> Salida estandar del subproceso: ");
        System.out.println("\n ================================================================ \n");
        
        String l;

        while ((l = br.readLine()) != null) {
            System.out.println(l);
        }
        System.out.println("\n ================================================================ \n");
    }
}

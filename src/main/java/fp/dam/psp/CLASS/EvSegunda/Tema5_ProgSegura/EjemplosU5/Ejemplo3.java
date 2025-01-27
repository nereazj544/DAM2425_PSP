package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.EjemplosU5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Ejemplo3 {
    public static void main(String[] args) throws Exception {
        File file = new File(System.getProperty("user.home") + "/Documents/fichero.txt");
    
        System.out.println("escribiendo ...");
        try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
            out.println("Línea 1");
            System.out.println("leyendo ...");
            
            try (BufferedReader in = new BufferedReader(new FileReader(file))) {
                String linea;
                while ((linea = in.readLine()) != null)
                    System.out.println(linea);
            }
        }
    }
}

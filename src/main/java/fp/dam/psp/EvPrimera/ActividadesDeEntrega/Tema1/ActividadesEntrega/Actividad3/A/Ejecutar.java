package fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema1.ActividadesEntrega.Actividad3.A;

import java.io.File;
import java.io.IOException;

public class Ejecutar {
    public static void main(String[] args) throws IOException {

        ProcessBuilder pb = new ProcessBuilder("java",
                "src\\main\\java\\fp\\dam\\psp\\EvPrimera\\Tema1\\ActividadesEntrega\\Actividad3\\A\\UnSaludo.java",
                "Arriba chuta la vicotria es tuya, Inazuna campeon");

        // TODO: ⤵️ este te lo crea dentro de la carpeta.
        String ruta = "src\\main\\java\\fp\\dam\\psp\\EvPrimera\\Tema1\\ActividadesEntrega\\Actividad3\\A";
        File file = new File(ruta, "\\saludo.txt");

        // File file = new File("saludo.txt");
        // TODO: ⤴️ este te lo crea en la raiz.

        pb.redirectOutput(file);
        

        try {
            Process p = pb.start();
            int e = p.waitFor();
            if (e == 0) {
                System.out.println("> Se ha termiando, revide el archivo: 'saludo.txt'");
            } else {
                System.out.println("> Error");
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

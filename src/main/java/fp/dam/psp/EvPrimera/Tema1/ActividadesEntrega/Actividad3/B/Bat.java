package fp.dam.psp.EvPrimera.Tema1.ActividadesEntrega.Actividad3.B;

import java.io.File;

public class Bat {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("> Se necesita que se pase un archivo '.bat'");
            System.exit(0);
        }

        String archviBat = args[0];

        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", archviBat);

        // TODO: Archivos para crear
        String ruta = "src\\main\\java\\fp\\dam\\psp\\EvPrimera\\Tema1\\ActividadesEntrega\\Actividad3\\B";
        File s = new File(ruta, "salida.txt");
        File er = new File(ruta, "error.txt");

        pb.redirectOutput(s);
        pb.redirectError(er);

        try {
            Process p = pb.start();
            System.out.println("> Ejecucion del archivo '.bat'.");
            System.out.println("> Revisar archivos 'salida.txt' y 'error.txt'.");
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}

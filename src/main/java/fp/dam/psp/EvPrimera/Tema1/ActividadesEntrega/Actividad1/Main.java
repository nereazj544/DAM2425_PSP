package fp.dam.psp.EvPrimera.Tema1.ActividadesEntrega.Actividad1;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ProcessBuilder pb = new ProcessBuilder("java",
                "src\\main\\java\\fp\\dam\\psp\\EvPrimera\\Tema1\\ActividadesEntrega\\Actividad1\\Actividad1.java");
        pb.redirectErrorStream(true);
        pb.redirectInput(ProcessBuilder.Redirect.INHERIT);

        try {
            Process p = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String l;

            while ((l = br.readLine()) != null) {
                System.out.println(l);
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

package fp.dam.psp.EvPrimera.tema3.DICIEMBRE.EJERCICIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLHTTP {

    public static void main(String[] args) {

        /*
         * if (args.length != 1) {
         * System.out.println("\033[31m¡NO SE HA INSERTADO EL URL DE LA PAGINA!\033[0m"
         * );
         * System.exit(0);
         * }
         */

        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("> Inserte una pagina: ");
            String sUrl = sc.nextLine();
            if (esUrl(sUrl)) {
                URL url = new URL(sUrl);
                System.out.println("> Has introduccido una URL valida: " + url.toString()
                        + "\n >SE PONDRA A DESCARGAR LAS IMAGENES: ");
                Descargar(sUrl);

            } else {
                System.out.println("\033[31m¡NO SE HA INSERTADO EL URL DE LA PAGINA!\033[0m");

            }

            // URL url = new URL(args[0]);

        } catch (Exception e) {
            e.getMessage();
        }

    }

    private static boolean esUrl(String sUrl) throws MalformedURLException {
        new URL(sUrl);
        return sUrl.startsWith("http://") || sUrl.startsWith("https://");
    }

    private static void Descargar(String sUrl) throws IOException {
        try {
            URL url = new URL(sUrl);
            URLConnection c = url.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));

            StringBuffer sb = new StringBuffer();
            String l;

            while ((l = br.readLine()) != null) {
                sb.append(l);
            }

            br.close();

            Pattern p = Pattern.compile("<img[^>]+src=\"([^\"]+)\"");
            Matcher m = p.matcher(sb.toString());

            new File("src\\main\\java\\fp\\dam\\psp\\EvPrimera\\tema3\\DICIEMBRE\\EJERCICIO\\img").mkdir();
            while (m.find()) {
                String img = m.group(1);
                if (!img.startsWith("http")) {
                    img = new URL(url, img).toString();
                }

                if (Img(img)) {
                    descargarImg(img);
                }
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void descargarImg(String img) throws MalformedURLException, IOException {
        URL url = new URL(img);
        String n = "img/";
        img.substring(img.lastIndexOf("/"));

        InputStream in = url.openStream();
        OutputStream out = new FileOutputStream(n);

        byte[] b = new byte[2048];
        int l;

        while ((l = in.read(b)) != -1) {
            out.write(b, 0, l);
        }

        in.close();
        out.close();

        System.out.println("> DESCARGADO: " + n);

    }

    private static boolean Img(String img) {

        String[] ex = { ".jpg", ".jpeg", ".png", ".gif", ".bmp", ".webp" };
        String u = img.toLowerCase();
        for (String string : ex) {
            if (u.endsWith(string))
                return true;

        }
        return false;

    }

}

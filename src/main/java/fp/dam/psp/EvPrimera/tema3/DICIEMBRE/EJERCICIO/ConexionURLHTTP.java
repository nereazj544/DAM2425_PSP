package fp.dam.psp.EvPrimera.tema3.DICIEMBRE.EJERCICIO;

import java.io.*;

import java.net.*;

import java.util.regex.*;

/*
 * Programa que reciba a través de un parámetro de línea de comando una URL de una página web y
descargue todas las imágenes referenciadas en el parámetro src de cada etiqueta <img> de dicha
página.
 */
public class ConexionURLHTTP {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Debe de añadir una URL");
            System.exit(0);
        }

        try {
            URL url = new URL(args[0]);
            URLConnection cn = url.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(cn.getInputStream()));

            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            Pattern p = Pattern.compile("img\\s+src=\"(.*?)\"");
            Matcher m = p.matcher(sb.toString());

            new File("img").mkdir();

            while (m.find()) {
                String imgURL = m.group(1);
                if (!imgURL.startsWith("http")) {
                    imgURL = new URL(url, imgURL).toString();
                }
                descargue(imgURL);
            }

        } catch (Exception e) {
            e.getMessage();
        }

    }

    private static void descargue(String imgURL) {
        try {
            URL url = new URL(imgURL);
            String file = "img/" + imgURL.substring(imgURL.lastIndexOf("/") + 1);

            InputStream in = url.openStream();
            OutputStream out = new FileOutputStream(file);

            byte[] b = new byte[2048];
            int l;

            while ((l = in.read(b)) != 1) {
                out.write(b, 0, l);
            }
            in.close();
            out.close();

            System.out.println("> DESCARGADO: " + file);

        } catch (Exception e) {
            e.getMessage();
        }
    }
}

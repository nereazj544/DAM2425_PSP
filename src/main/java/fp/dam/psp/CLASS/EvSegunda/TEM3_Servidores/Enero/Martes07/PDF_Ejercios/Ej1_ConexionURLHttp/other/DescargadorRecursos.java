package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Ejercios.Ej1_ConexionURLHttp.other;



import java.io.*;
import java.net.*;
import java.util.regex.*;

public class DescargadorRecursos {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java DescargadorRecursos <URL>");
            return;
        }

        try {
            URL url = new URL(args[0]);
            URLConnection conexion = url.openConnection();
            
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(conexion.getInputStream())
            );
            
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea);
            }
            reader.close();

            // Buscar cualquier etiqueta con atributo src
            Pattern pattern = Pattern.compile("src=\"(.*?)\"");
            Matcher matcher = pattern.matcher(contenido.toString());

            // Crear directorio para los recursos
            new File("recursos").mkdir();

            // Descargar cada recurso encontrado
            while (matcher.find()) {
                String urlRecurso = matcher.group(1);
                if (!urlRecurso.startsWith("http")) {
                    urlRecurso = new URL(url, urlRecurso).toString();
                }
                descargarRecurso(urlRecurso);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void descargarRecurso(String urlRecurso) {
        try {
            URL url = new URL(urlRecurso);
            String nombreArchivo = "recursos/" + 
                urlRecurso.substring(urlRecurso.lastIndexOf("/") + 1);

            InputStream in = url.openStream();
            OutputStream out = new FileOutputStream(nombreArchivo);

            byte[] buffer = new byte[2048];
            int length;

            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();

            System.out.println("Recurso descargado: " + nombreArchivo);
        } catch (Exception e) {
            System.out.println("Error al descargar: " + urlRecurso);
            e.printStackTrace();
        }
    }
}
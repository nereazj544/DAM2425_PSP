package fp.dam.psp.EvPrimera.tema3.DICIEMBRE.DIA02;

import java.net.URL;

public class EjemploUrl {
    public static void main(String[] args) {
        URL url;

        try {
            System.out.println("CONSTRUCION SIMPLE URL: ");
            url= new URL("http://docs.oracle.com/");
            Visualizar(url);
            System.out.println("Otro constructor simple para una URL:");
            url = new URL("http://www.example.com/action.php?name=john&age=24");
            Visualizar(url);
            System.out.println("Constructor para protocolo + URL + directorio:");
            url = new URL("http", "docs.oracle.com", "/javase/7");
            Visualizar(url);
            System.out.println("Constructor para protocolo + URL + puerto + directorio:");
            url = new URL("http", "docs.oracle.com", 80, "/javase/7");
            Visualizar(url);
            System.out.println("Constructor para un objeto URL y un directorio:");
            URL urlBase = new URL("http://docs.oracle.com/");
            url = new URL(urlBase, "/javase/7/docs/api/java/net/URL.html");
            Visualizar(url);

        } catch (Exception e) {
            e.getMessage();
        }
    }

    private static void Visualizar(URL url) {
        System.out.println("\tURL completa: " + url.toString());
        System.out.println("\tgetProtocol(): " + url.getProtocol());
        System.out.println("\tgetHost(): " + url.getHost());
        System.out.println("\tgetPort(): " + url.getPort());
        System.out.println("\tgetFile(): " + url.getFile());
        System.out.println("\tgetUserlnfo(): " + url.getUserInfo());
        System.out.println("\tgetPath(): " + url.getPath());
        System.out.println("\tgetAuthority(): " + url.getAuthority());
        System.out.println("\tgetQuery(): " + url.getQuery());
        System.out.println("==================================================");
    }
}

package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Viernes10.ConexionUrlV1;

/*
* Idea principal de esto es conectarse a un HTTP, usando los objetos URL y HttpURLConnection
* EJERCICIO 1: Programa que reciba a través de un parámetro de línea de comando una URL de una página web y
* descargue todas las imágenes referenciadas en el parámetro src de cada etiqueta <img> de dicha página.
*/

import java.io.IOException;
import java.net.*;

public class ConexionURL {
    public static void main(String[] args) throws URISyntaxException, MalformedURLException, IOException {
        // ! Al parecer URL se quedo obsoleto
        URL url = new URI(args[0]).toURL();

        //? Un poco rollo parece
        HttpURLConnection connectionURL = (HttpURLConnection) url.openConnection();




    }
}

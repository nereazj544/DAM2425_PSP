package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Viernes10.ConexionUrlV1;

/*
* Idea principal de esto es conectarse a un HTTP, usando los objetos URL y HttpURLConnection
* EJERCICIO 1: Programa que reciba a través de un parámetro de línea de comando una URL de una página web y
* descargue todas las imágenes referenciadas en el parámetro src de cada etiqueta <img> de dicha página.
*/

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class ConexionURL {
    public static void main(String[] args) throws URISyntaxException, MalformedURLException, IOException {
        // ! Al parecer URL se quedo obsoleto
        URL url = new URI(args[0]).toURL();

        //? Un poco rollo parece
        HttpURLConnection connectionURL = (HttpURLConnection) url.openConnection();

        // Se puede hacer por el metodo get, put (varios, para hacer las peticiones) no se hara en este caso.

        int responseCode = connectionURL.getResponseCode(); //! para ver si hay respuesta
        if (responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader br = new BufferedReader(new InputStreamReader(connectionURL.getInputStream()));

            String l;

            while ((l = br.readLine()) != null){
                System.out.println(l);

            }

            connectionURL.disconnect();
        }

    //! Usar una expresion regular para mostrar solo las direcciones de la pagina web, y descargar las imagenes para almacenarlas en algun lado.



    }
}

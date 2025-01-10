package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Viernes10.ConexionUrlV2;

/*
Alternativa del ejercicio para API
Pagina: dogapi.com

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class ConexionURL_Api {
        public static void main(String[] args) throws URISyntaxException, MalformedURLException, IOException {
            // ! Al parecer URL se quedo obsoleto
            URL url = new URI("https://dog-api.kinduff.com/api/facts?number=10").toURL(); // Nos mostrara un json 😶💧🔫


            //? Un poco rollo parece
            HttpURLConnection connectionURL = (HttpURLConnection) url.openConnection();

            // Se puede hacer por el metodo get, put (varios, para hacer las peticiones) no se hara en este caso.
            connectionURL.setRequestMethod("GET");
            int responseCode = connectionURL.getResponseCode(); //! para ver si hay respuesta
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connectionURL.getInputStream()));

                String l;

                while ((l = br.readLine()) != null) {
                    System.out.println(l);

                }
                connectionURL.disconnect();
            }
        }
    }


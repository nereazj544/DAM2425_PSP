package fp.dam.psp.CLASS.EvSegunda.ActivdadConTest.EjemplodeClase;

/*
Alternativa del ejercicio para API
Pagina: dogapi.com

 */

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.stream.Collectors;

public class ConexionURL_Api {
    public static void main(String[] args) throws URISyntaxException, MalformedURLException, IOException {
        // ! Al parecer URL se quedo obsoleto
        URL url = new URI("https://dog-api.kinduff.com/api/facts?number=10").toURL(); // Nos mostrara un json 😶💧🔫

        // ? Un poco rollo parece
        HttpURLConnection connectionURL = (HttpURLConnection) url.openConnection();

        // Se puede hacer por el metodo get, put (varios, para hacer las peticiones) no
        // se hara en este caso.
        connectionURL.setRequestMethod("GET");
        int responseCode = connectionURL.getResponseCode(); // ! para ver si hay respuesta
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(connectionURL.getInputStream()));
            Gson gson = new Gson();
            JsonRespuesta jsR = gson.fromJson(
                    br.lines().collect(Collectors.joining()), JsonRespuesta.class

            );
            int c = 1;
            for (String fact : jsR.facts) {
                System.out.print("> " + c++ + " ");
                System.out.println(fact);
            }

            connectionURL.disconnect();

            connectionURL.disconnect();

        } else {
            System.out.println(responseCode);

        }
    }
}

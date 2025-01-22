package fp.dam.psp.CLASS.EvSegunda.ActivdadConTest.Other.PokemonApi.V1;

import java.io.*;
import java.net.*;
import java.util.*;

import com.google.gson.Gson;

public class PokeApi {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("> Introduce el nombre del pokemon a buscar");
            System.out.print("> ");

            String pokeBus = sc.nextLine().toLowerCase();

            String ul = "https://pokeapi.co/api/v2/pokemon/" + pokeBus;

            URL url = new URL(ul);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int res = connection.getResponseCode();

            if (res == 200) {
                // DataInputStream in = new DataInputStream(connection.getInputStream());
                BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();

                String l;

                while ((l = bf.readLine()) != null) {
                    sb.append(l);
                }

                bf.close();

                Gson gs = new Gson();

                Pokemon pokemon = gs.fromJson(sb.toString(), Pokemon.class);

                System.out.println("====================================");
                System.out.println("- Nombre del pokemon: " + pokemon.name);
                for (Pokemon.TypesWrapper t : pokemon.types) {
                    System.out.println("> Tipo: " + t.type.name);
                }

                System.out.println("====================================");

                System.out.println("- Habilidades: ");

                for (Pokemon.AbilityWrapper h : pokemon.abilities) {
                    System.out.println("> " + h.ability.name);

                }

                System.out.println("====================================");
                System.out.println("- Estadisticas");

                for (Pokemon.StatsWrapper s : pokemon.stats) {
                    System.out.println("> " + s.stat.name + ": " + s.base_stat);

                }
                System.out.println("====================================");

                connection.disconnect();

            } else if (res == 404) {
                System.out.println("> Error: el nombre del Pokemon esta mal escrito o no existe");
            } else {
                System.out.println("> Error (Codigo de respuesta " + res + ")");
            }

        } catch (Exception e) {
            System.out.println("> Error; más informacion: " + e.getMessage());
        }
    }
}
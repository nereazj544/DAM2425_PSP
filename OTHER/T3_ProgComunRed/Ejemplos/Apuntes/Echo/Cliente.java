package T3_ProgComunRed.Ejemplos.Apuntes.Echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	public static void main(String[] args) {
		String Host = "localhost";
		int Puerto = 6000;
		Socket cliente = null;
		PrintWriter writer = null;
		BufferedReader reader = null;
		try {
			cliente = new Socket(Host, Puerto);

			// CREO FLUJO DE SALIDA AL SERVIDOR
			writer = new PrintWriter(cliente.getOutputStream(), true);

			// CREO FLUJO DE ENTRADA AL SERVIDOR
			reader = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

			// FLUJO PARA ENTRADA ESTANDAR
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			String cadena, eco;
			System.out.print("Introduce cadena: ");
			cadena = in.readLine();
			while (cadena != null) {
				writer.println(cadena);
				eco = reader.readLine();
				System.out.println(" =>ECO: " + eco);
				System.out.print("Introduce cadena: ");
				cadena = in.readLine();
			}
			in.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				writer.close();
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			System.out.println("Fin del envió... ");
			if (cliente != null)
				try {
					cliente.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}

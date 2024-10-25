package T3_ProgComunRed.Ejemplos.Apuntes.Echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		ServerSocket servidor = null;
		Socket cliente = null;
		PrintWriter writer = null;
		BufferedReader reader = null;
		try {
			servidor = new ServerSocket(6000);
			System.out.println("Esperando conexion...");
			cliente = servidor.accept();
			System.out.println("Cliente conectado...");

			// CREO FLUJO DE SALIDA AL CLIENTE
			writer = new PrintWriter(cliente.getOutputStream(), true);
			reader = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			String cadena;
			while ((cadena = reader.readLine()) != null) {
				writer.println(cadena);
				System.out.println("Recibiendo: " + cadena);
				if (cadena.equals("*"))
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// CERRAR STREAMS Y SOCKETS
			System.out.println("Cerrando conexión...");
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			writer.close();
			if (cliente != null)
				try {
					cliente.close();
				} catch (IOException e) { 
					e.printStackTrace();
				}
			if (servidor != null)
				try {
					servidor.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}

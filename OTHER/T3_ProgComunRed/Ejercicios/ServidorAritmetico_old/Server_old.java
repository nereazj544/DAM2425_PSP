package T3_ProgComunRed.Ejercicios.ServidorAritmetico_old;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Server_old {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("SERVER");
		while (true) {
			Socket socket = serverSocket.accept();
			try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) { // esto es un
				String line;
				while ((line = in.readLine()) != null) {
					Float retNum = null;
					String[] array = line.split(" ");
					if (array.length == 3) {
						switch (array[0].toLowerCase()) {
						case "suma":
							System.out.println("REALIZANDO SUMA...");
							retNum = Float.parseFloat(array[1]) + Float.parseFloat(array[2]);
							break;
						case "resta":
							System.out.println("REALIZANDO RESTA...");
							retNum = Float.parseFloat(array[1]) - Float.parseFloat(array[2]);
							break;
						case "multiplicacion":
							System.out.println("REALIZANDO MULTIPLICACION...");
							retNum = Float.parseFloat(array[1]) * Float.parseFloat(array[2]);
							break;
						case "division":
							System.out.println("REALIZANDO DIVISION...");
							retNum = Float.parseFloat(array[1]) / Float.parseFloat(array[2]);
							break;
						default:
							System.out.println("COMANDO INCORRECTO");
							out.println("No se ha reconocido la operación, introduzcala de nuevo");
						}
						out.println(retNum);
						out.flush();
					} else {
						out.println(
								"No se ha reconocido ninguna operación que se pueda llevar a cabo, reescriba el comando");
					}
				}
			} catch (Exception e) {
				System.out.println("Se ha cerrado la conexión con el cliente");
			}

		}

	}

}

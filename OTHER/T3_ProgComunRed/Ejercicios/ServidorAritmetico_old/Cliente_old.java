package T3_ProgComunRed.Ejercicios.ServidorAritmetico_old;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class Cliente_old {
	
	public static void main(String[] args) throws IOException {
//		BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		System.out.println("CLIENTE");
		System.out.println("nombre_operación número número");
		System.out.print("> ");
		while ((line = Main.textoStr.toString()) != null) {
			Socket socket = new Socket("localhost", 9999); 
			try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
					PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {
				out.println(line);
				out.flush();
//				Main.textoStr = in.readLine();
				System.out.println(line);
				System.out.println(Main.textoStr);
//				System.out.println(in.readLine());
			} catch (Exception e) {
				System.out.println("Se ha cerrado la conexión con el servidor");
			}
			
		}
	}
	
}

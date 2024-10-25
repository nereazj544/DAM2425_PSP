package T3_ProgComunRed.Ejemplos.P03_ServerEcho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
	
	public static void main(String[] args) throws IOException {
		BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));
		String line;
		System.out.print("> ");
		while ((line = keyboardIn.readLine()) != null) {
			Socket socket = new Socket("localhost", 9999); //ya se ha establecido la conexion con el servidor
			try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
					PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {
				//CUIDADO CON LO QUE SE UTILIZA; no funciona el printwriter por si solo ya que espera a que este lleno de datos
				out.println(line);
				out.flush();
				System.out.println(in.readLine());
				System.out.print("> ");
			}
			
		}
	}
	
}

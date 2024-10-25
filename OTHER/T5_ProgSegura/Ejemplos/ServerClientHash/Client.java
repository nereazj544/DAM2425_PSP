package T5_ProgSegura.Ejemplos.ServerClientHash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class Client {
	public static void main(String[] args) throws IOException {
		BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));
		String line;
		System.out.print("> ");
		while ((line = keyboardIn.readLine()) != null) {
			Socket socket = new Socket("192.168.1.252", 9999); 
			try (InputStream in = socket.getInputStream(); 
					OutputStream out = socket.getOutputStream()) {
				out.write(line.getBytes());
				socket.shutdownOutput(); //ESTO MARCA EL FIN DEL FICHERO, si no no funciona
				System.out.println(Arrays.toString(in.readAllBytes()));
				System.out.print("> ");
			}
			
		}
	}
}

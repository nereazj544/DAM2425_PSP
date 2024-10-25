package T3_ProgComunRed.Ejercicios.ServidorAritmetico_old;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
	public static void main(String[] args) throws IOException {
		StringBuilder line;
		System.out.print("> ");
		while ((line = Main.sb) != null) {
			Socket socket = new Socket("localhost", 9999); //ya se ha establecido la conexion con el servidor
			try (DataInputStream in = new DataInputStream(socket.getInputStream()); 
					DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
				out.writeUTF(line.toString());
				out.flush();
				System.out.println(in.readUTF());
				System.out.print("> ");
			}
			
		}
	}
}

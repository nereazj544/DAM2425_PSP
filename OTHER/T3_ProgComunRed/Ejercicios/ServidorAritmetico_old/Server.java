package T3_ProgComunRed.Ejercicios.ServidorAritmetico_old;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		// servidor conectado al puerto 9999
		ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("ECHO");
		// este bucle acepta las peticiones segun vayan llegando
		while (true) {
			Socket socket = serverSocket.accept(); // es un listener que espera a que haya peticion de conexion. segunhaya una, la acepta
			try (DataInputStream in = new DataInputStream(socket.getInputStream());
					DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
				String line = "";
				while ((line = in.readUTF()) != null) {
					out.writeUTF(line.toString());
					out.flush();
					System.out.println(line);
				}
			}

		}
	}
}

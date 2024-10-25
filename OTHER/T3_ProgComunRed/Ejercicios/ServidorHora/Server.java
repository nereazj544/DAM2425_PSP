package T3_ProgComunRed.Ejercicios.ServidorHora;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(9999);
		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println("SERVER: socket aceptado");
			try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {
				System.out.println("SERVER: enviando hora " + LocalTime.now());
				out.println(LocalTime.now());
				out.flush();
			}
		}

	}

}

package T3_ProgComunRed.Ejercicios.TicTacToe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		// servidor conectado al puerto 9999
		ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("ECHO");
		String line = "";
		while (true) {
			Socket socket1 = serverSocket.accept();
			Socket socket2 = serverSocket.accept();
			try (BufferedReader in = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
					PrintWriter out = new PrintWriter(new OutputStreamWriter(socket1.getOutputStream()));
					BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
					PrintWriter out2 = new PrintWriter(new OutputStreamWriter(socket2.getOutputStream()))) {
//				InAndOut(in, out2);
//				InAndOut(in2, out);
				line = in.readLine();
				System.out.println(line);
				if (line != null) {
					out2.write(line);
					out2.flush();
				}
				line = in2.readLine(); 
				System.out.println(line);
				if (line != null) {
					out.write(line);
					out.flush();
				}
			}

//			try (BufferedReader in = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
//					PrintWriter out = new PrintWriter(new OutputStreamWriter(socket1.getOutputStream()))) {
//				line = in.readLine();
//				System.out.println(line);
//				if (line != null) {
//					out.write(line);
//					out.flush();
//				}
//			}
//			try (BufferedReader in = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
//					PrintWriter out = new PrintWriter(new OutputStreamWriter(socket2.getOutputStream()))) {
//				line = in.readLine();
//				System.out.println(line);
//				if (line != null) {
//					out.write(line);
//					out.flush();
//				}
//			}
		}
	}

	public static void InAndOut(BufferedReader in, PrintWriter out) throws IOException {
		String line;
		if ((line = in.readLine()) != null) {
			out.println(line);
			out.flush();
			System.out.println(line);
		}
	}
}

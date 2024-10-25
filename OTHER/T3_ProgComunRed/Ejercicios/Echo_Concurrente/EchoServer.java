package T3_ProgComunRed.Ejercicios.Echo_Concurrente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EchoServer {

	public static ServerSocket serverSocket;

	public static void main(String[] args) throws IOException {
		serverSocket = new ServerSocket(9999);
		System.out.println("ECHO");
		ExecutorService executor = Executors.newFixedThreadPool(100);
//		for (int i = 0; i < 10; i++)
//			executor.submit(new Thread(new Clase()));
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public class Clase implements Runnable {
		@Override
		public void run() {
			Socket socket;
			try {
				socket = serverSocket.accept();
				try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) { // esto
					String line;
					while ((line = in.readLine()) != null) {
						out.println(line);
						out.flush();
						System.out.println(line);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

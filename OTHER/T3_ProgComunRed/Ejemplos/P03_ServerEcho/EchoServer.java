package T3_ProgComunRed.Ejemplos.P03_ServerEcho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) throws IOException { 
		//servidor conectado al puerto 9999
		ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("ECHO");
		//este bucle acepta las peticiones segun vayan llegando 
		while (true) {
			Socket socket = serverSocket.accept(); //es un listener que espera a que haya peticion de conexion. segun haya una, la acepta
			try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
					PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) { //esto es un try with resources. basicamente lo que hace es asegurarse de que los recursos (separados con ;) metidos en el sean cerrados al final de todo lo que se ejecute en el try
				String line; 
				while ((line = in.readLine()) != null) {
					out.println(line);
					out.flush();
					System.out.println(line);
				}
			}
			
		}
		
	}

}

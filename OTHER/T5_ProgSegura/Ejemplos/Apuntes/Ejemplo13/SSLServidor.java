package T5_ProgSegura.Ejemplos.Apuntes.Ejemplo13;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class SSLServidor {
	public static void main(String[] arg) throws IOException {
		SSLServerSocket serverSocket = null;
		try {
			serverSocket = (SSLServerSocket) SSLServerSocketFactory.getDefault().createServerSocket(6000);
			while (true)
				atenderPeticion((SSLSocket) serverSocket.accept());
		} finally {
			if (serverSocket != null)
				serverSocket.close();
		}
	}

	static void atenderPeticion(SSLSocket socket) throws IOException {
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			System.out.println("Recibido: " + in.readUTF());
			out.writeUTF("Saludos del servidor");
		} finally {
			socket.close();
		}
	}
}

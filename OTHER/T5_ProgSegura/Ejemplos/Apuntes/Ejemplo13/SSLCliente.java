package T5_ProgSegura.Ejemplos.Apuntes.Ejemplo13;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class SSLCliente {
	public static void main(String[] args) throws IOException {
		SSLSocket socket = null;
		try {
			socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket("localhost", 6000);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF("Saludos del cliente");
			DataInputStream in = new DataInputStream(socket.getInputStream());
			System.out.println("Recibido: " + in.readUTF());
		} finally {
			if (socket != null)
				socket.close();
		}
	}
}

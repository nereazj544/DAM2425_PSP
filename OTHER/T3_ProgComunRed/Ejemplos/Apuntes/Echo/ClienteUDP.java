package T3_ProgComunRed.Ejemplos.Apuntes.Echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteUDP {
	public static void main(String[] args) {
		try {
			InetAddress destino = InetAddress.getLocalHost();
			int puerto = 12345;
			byte[] mensaje = "Enviando Saludos !!".getBytes();
			DatagramPacket datagrama = new DatagramPacket(mensaje, mensaje.length, destino, puerto);
			DatagramSocket socket;
			try {
				socket = new DatagramSocket(34567);
				try {
					socket.send(datagrama);
					System.out.println("Enviando Datagrama de longitud: " + mensaje.length);
					System.out.println("Host remoto : " + destino.getHostName());
					System.out.println("IP remota : " + destino.getHostAddress());
					System.out.println("Puerto local: " + socket.getLocalPort());
					System.out.println("Puerto remoto: " + datagrama.getPort());
				} catch (IOException e) {
					e.printStackTrace();
				}
				socket.close();
			} catch (SocketException e) {
				e.printStackTrace();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
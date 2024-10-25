package T3_ProgComunRed.Ejemplos.Apuntes.Echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServidorUDP {
	public static void main(String[] args) {
		byte[] bufer = new byte[1024];
		DatagramSocket socket;
		try {
			socket = new DatagramSocket(12345);
			System.out.println("Esperando Datagrama ...");
			DatagramPacket datagrama = new DatagramPacket(bufer, bufer.length);
			try {
				socket.receive(datagrama);
				int bytesRecibidos = datagrama.getLength();
				String paquete = new String(datagrama.getData());
				System.out.println("Numero de Bytes recibidos: " + bytesRecibidos);
				System.out.println("Contenido del Paquete : " + paquete.trim());
				System.out.println("Puerto de origen: " + datagrama.getPort());
				System.out.println("IP de origen : " + datagrama.getAddress().getHostAddress());
				System.out.println("Puerto local:" + socket.getLocalPort());
			} catch (IOException e) {
				e.printStackTrace();
			}
			socket.close();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
}

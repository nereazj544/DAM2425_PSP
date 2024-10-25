package T3_ProgComunRed.Ejemplos.Apuntes.Echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClienteMulticast {
	public static void main(String[] args) {
		try {
			int Puerto = 12345;
			MulticastSocket ms = new MulticastSocket(Puerto);
			InetAddress grupo = InetAddress.getByName("225.0.0.1");
			ms.joinGroup(grupo);
			String msg;
			byte[] buf = new byte[1000];
			DatagramPacket datagrama = new DatagramPacket(buf, buf.length);
			do {
				ms.receive(datagrama);
				msg = new String(datagrama.getData(), 0, datagrama.getLength());
				System.out.println("Recibo: " + msg.trim());
			} while (!msg.trim().equals("*"));
			ms.leaveGroup(grupo);
			ms.close();
			System.out.println("Socket cerrado...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
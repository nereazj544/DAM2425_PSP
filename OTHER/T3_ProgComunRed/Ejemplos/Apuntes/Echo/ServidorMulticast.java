package T3_ProgComunRed.Ejemplos.Apuntes.Echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ServidorMulticast {
	public static void main(String[] args) {
		MulticastSocket ms = null;
		try {
			ms = new MulticastSocket();
			int puerto = 12345;
			InetAddress grupo = InetAddress.getByName("225.0.0.1");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String linea;
			do {
				System.out.print("Datos a enviar al grupo: ");
				linea = br.readLine();
				DatagramPacket datagrama = new DatagramPacket(linea.getBytes(), linea.length(), grupo, puerto);
				ms.send(datagrama);
			} while (!linea.trim().equals("*"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ms != null) {
				ms.close();
				System.out.println("Socket cerrado...");
			}
		}
	}
}

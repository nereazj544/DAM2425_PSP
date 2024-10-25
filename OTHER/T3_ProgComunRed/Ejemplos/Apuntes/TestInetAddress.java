package T3_ProgComunRed.Ejemplos.Apuntes;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddress {
	public static void main(String[] args) {
		try {
			pruebaMetodos(InetAddress.getLocalHost(), "getLocalHost()");
			pruebaMetodos(InetAddress.getLoopbackAddress(), "getLoopbackAddress()");
			String host = "www.google.com";
			pruebaMetodos(InetAddress.getByName(host), "getByName()");
			obtenerTodas(host);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private static void obtenerTodas(String host) {
		System.out.println("======================================================");
		System.out.println("Direcciones obtenidas con el método getAllByName()");
		try {
			InetAddress[] direcciones = InetAddress.getAllByName(host);
			for (InetAddress d : direcciones)
				System.out.println("\t" + d.toString());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println("======================================================");
	}

	private static void pruebaMetodos(InetAddress dir, String metodo) {
		System.out.println("======================================================");
		System.out.println("Dirección obtenida con el método " + metodo);
		System.out.println("\tMétodo getHostName(): " + dir.getHostName());
		System.out.println("\tMétodo getHostAddress(): " + dir.getHostAddress());
		System.out.println("\tMétodo toString(): " + dir.toString());
		System.out.println("\tMétodo getCanonicalHostName(): " + dir.getCanonicalHostName());
	}
}
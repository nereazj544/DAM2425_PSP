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
		System.out.println("Direcciones obtenidas con el m�todo getAllByName()");
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
		System.out.println("Direcci�n obtenida con el m�todo " + metodo);
		System.out.println("\tM�todo getHostName(): " + dir.getHostName());
		System.out.println("\tM�todo getHostAddress(): " + dir.getHostAddress());
		System.out.println("\tM�todo toString(): " + dir.toString());
		System.out.println("\tM�todo getCanonicalHostName(): " + dir.getCanonicalHostName());
	}
}
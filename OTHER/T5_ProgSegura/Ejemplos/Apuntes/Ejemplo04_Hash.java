package T5_ProgSegura.Ejemplos.Apuntes;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Ejemplo04_Hash {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-256");
		md.update("un mensaje de prueba".getBytes(StandardCharsets.UTF_8));
		byte[] resumen = md.digest();
		System.out.println("Algoritmo: " + md.getAlgorithm());
		System.out.println("Proveedor: " + md.getProvider().toString());
		System.out.println("Longitud del resumen: " + md.getDigestLength());
		System.out.println("Resumen en Hexadecimal: " + hexadecimal(resumen));
		System.out.println("Resumen en Base64: " + Base64.getEncoder().encodeToString(resumen));
	}

	static String hexadecimal(byte[] resumen) {
		StringBuilder hex = new StringBuilder();
		for (int i = 0; i < resumen.length; i++)
			hex.append(String.format("%02X", resumen[i]));
		return hex.toString();
	}
}

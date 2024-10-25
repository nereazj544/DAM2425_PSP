package T5_ProgSegura.Ejemplos.Apuntes;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Ejemplo07_CipherSecretKey {

	/* Algunos modos de algoritmo, como por ejemplo CBC, necesitan pasar al método
	   init un vector de inicialización para el proceso de descifrado. El vector de
	   inicialización se ha de obtener en el proceso de cifrado con la ayuda de la
	   clase IvParameterSpec. El ejemplo siguiente es una variación del ejemplo
	   anterior usando el algoritmo "AES/CBC/PKCS5Padding" para cifrar y descifrar: */

	public static void main(String[] args) throws Exception {
		/* creación de una clave secreta AES de 256 bits */
		KeyGenerator kg = KeyGenerator.getInstance("AES");
		kg.init(256);
		SecretKey clave = kg.generateKey();

		/* creación de un objeto Cipher que usará el algoritmo de cifrado
		   "AES/ECB/PKCS5Padding" */
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

		/* inicialización del objeto Cipher para cifrar con la clave secreta */
		cipher.init(Cipher.ENCRYPT_MODE, clave);

		/* obtención del vector de inicialización */
		IvParameterSpec iv = new IvParameterSpec(cipher.getIV());

		/* obtención del texto cifrado a partir del texto original */
		byte[] textoCifrado = cipher.doFinal("TOP SECRET".getBytes());
		System.out.println("Texto cifrado: " + new String(textoCifrado));

		/* re-inicialización del objeto Cipher para cifrar con la misma clave secreta y
		   el mismo vector de inicialización */
		cipher.init(Cipher.DECRYPT_MODE, clave, iv);

		/* Obtención del texto original a partir del texto cifrado */
		System.out.println("Texto original: " + new String(cipher.doFinal(textoCifrado)));
	}
}

package T5_ProgSegura.Ejemplos.Apuntes;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Ejemplo06_Cipher {
	
	/*los proveedores de seguridad registrados en la plataforma java implementan los siguientes algoritmos de cifrado simetrico
	 * - DES (Data Encryption Standart) (MALA SEGURIDAD)
	 * - DESede (aplica DES tres veces) (NO RECOMENDADO)
	 * - AES (Advanced Encryption Standart) sustituyo a DES, claves longitud 128, 192 o 256 bits
	 * - PBE (Password Based Encryption) puede ser usado con algoritmos clave priv y funciones resumen
	 */
	
	public static void main(String[] args) throws Exception {
		/* crear de una clave secreta AES de 256bits */
		KeyGenerator kg = KeyGenerator.getInstance("AES");
		kg.init(256);
		SecretKey clave = kg.generateKey();
		
		/* crear un objeto Cipher que usará el algoritomo de cifrado "AES/ECB/PKCS5Padding" */
		Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
		
		/* inicializar el objeto Cipher para cifrar con la clave secreta */
		c.init(Cipher.ENCRYPT_MODE, clave);
		
		/* obtener del texto cifrado a partir del texto original */
		byte[] textoCifrado = c.doFinal("TOP SECRET".getBytes());
		System.out.println("Texto cifrado: " + new String(textoCifrado));
		
		/* reinicializar el objeto Cipher para descifrar con la misma clave secreta */
		c.init(Cipher.DECRYPT_MODE, clave);
		
		/* obtener el texto original a partir del texto cifrado */
		System.out.println("Texto original: " + new String(c.doFinal(textoCifrado)));
	}
}

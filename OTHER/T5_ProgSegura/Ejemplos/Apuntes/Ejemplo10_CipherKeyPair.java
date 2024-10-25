package T5_ProgSegura.Ejemplos.Apuntes;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Ejemplo10_CipherKeyPair {
	public static void main(String args[]) throws Exception {
		/* Creación del par de claves RSA */
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(2048);
		KeyPair par = keyGen.generateKeyPair();
		PrivateKey clavePrivada = par.getPrivate();
		PublicKey clavePublica = par.getPublic();
		
		/* Creación de la clave secreta AES para cifrado simétrico */
		KeyGenerator kg = KeyGenerator.getInstance("AES");
		kg.init(256);
		SecretKey claveSecreta = kg.generateKey();
		
		/* Cifrado de la clave secreta AES con la clave pública RSA */
		Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		c.init(Cipher.WRAP_MODE, clavePublica);
		byte claveCifrada[] = c.wrap(claveSecreta);
		
		/* Cifrado del texto con la clave secreta */
		c = Cipher.getInstance("AES/ECB/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, claveSecreta);
		byte textoCifrado[] = c.doFinal("jejejejejje".getBytes());
		System.out.println("Cifrado: " + new String(textoCifrado));
		
		/* Descifrado de la clave secreta AES con la clave RSA privada */
		Cipher c2 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		c2.init(Cipher.UNWRAP_MODE, clavePrivada);
		Key claveDescifrada = c2.unwrap(claveCifrada, "AES", Cipher.SECRET_KEY);
		
		/* Descifrado del texto con la clave secreta */
		c2 = Cipher.getInstance("AES/ECB/PKCS5Padding");
		c2.init(Cipher.DECRYPT_MODE, claveDescifrada);
		byte texto[] = c2.doFinal(textoCifrado);
		System.out.println("Descifrado: " + new String(texto));
	}
}

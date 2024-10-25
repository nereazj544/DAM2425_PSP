package T5_ProgSegura.Ejemplos.Apuntes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;

public class Ejemplo11_CipherOutputStream {
	public static void main(String[] args) throws Exception {
		/* Recuperamos la clave secreta del fichero */
		File file = new File(System.getProperty("user.home") + "/miclave.key");
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
			SecretKey clave = (SecretKey) in.readObject();
			System.out.println(clave.getAlgorithm());
			
			/* Creamos un objeto Cipher para cifrar con el algoritmo AES */
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, clave);
			
			/*
			 * Se crean los streams de entrada salida para el fichero pdf y el fichero
			 * cifrado y se lleva a cabo el proceso de cifrado
			 */
			File original = new File(System.getProperty("user.home") + "/unidad5.pdf");
			File cifrado = new File(System.getProperty("user.home") + "/cifrado.pdf");
			try (FileInputStream fis = new FileInputStream(original);
					CipherOutputStream out = new CipherOutputStream(new FileOutputStream(cifrado), cipher)) {
				/* Creamos un buffer con el tamaño de bloque del objeto Cipher */
				byte[] bloque = new byte[cipher.getBlockSize()];
				
				/* Leemos bloque a bloque y lo guardamos cifrado */
				int n;
				while ((n = fis.read(bloque)) != -1)
					out.write(bloque, 0, n);
			}
		}
	}
}

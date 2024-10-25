package T5_ProgSegura.Ejemplos.Apuntes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Ejemplo08_AlmcClavesSimFich {
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		KeyGenerator kg = KeyGenerator.getInstance("AES");
		kg.init(256);
		SecretKey clave = kg.generateKey();
		File file = new File(System.getProperty("user.home") + "/miclave.key");
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
			out.writeObject(clave);
		}
	}
}

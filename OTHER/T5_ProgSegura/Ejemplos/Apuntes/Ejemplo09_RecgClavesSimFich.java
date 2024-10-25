package T5_ProgSegura.Ejemplos.Apuntes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.crypto.SecretKey;

public class Ejemplo09_RecgClavesSimFich {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		File file = new File(System.getProperty("user.home") + "/miclave.key");
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
			SecretKey clave = (SecretKey) in.readObject();
			System.out.println(clave.getAlgorithm());
		}
	}
}

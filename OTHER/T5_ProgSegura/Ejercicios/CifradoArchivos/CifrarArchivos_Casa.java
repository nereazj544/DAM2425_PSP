package T5_ProgSegura.Ejercicios.CifradoArchivos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class CifrarArchivos_Casa {
	public static void main(String args[]) throws Exception {
		KeyStore ks = KeyStore.getInstance("pkcs12");
		char[] password = "TardeDAM123".toCharArray();
		ks.load(new FileInputStream("C:\\cygwin64\\home\\Tarde\\certs\\keystore.p12"), password);
//		ks.load(new FileInputStream("C:\\cygwin64\\home\\buru\\certs\\keystore.p12"), password);
		
		PrivateKey privKey = (PrivateKey) ks.getKey("miclave", "TardeDAM123".toCharArray()); 
		PublicKey pubKey = (PublicKey) ks.getCertificate("miclave").getPublicKey();
		// ^^ codigo cogido del ejemplo "KeyStore"
		
		//EJERCICIO
//		Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		Cipher c = Cipher.getInstance("RSA/ECB/NoPadding"); 
			//FIXME da error con padding
			/* por lo que he encontrado en internet, es porque PKCS1Padding añade 11 bytes al archivo
			 * esto hace que el peso del archivo es de 512 bytes en vez de los 501 que pesaria sin padding
			 * esto resulta en una exception IllegalBlockSizeException por el tamaño del bloque
			 * usar NoPadding reduce la seguridad (que no es bueno) pero soluciona la exception :b
			 * * usar NoPadding tambien retorna 512 bytes por alguna razon?? pero llevo horas con esto y me la suda bastante tbh
			 */
		c.init(Cipher.ENCRYPT_MODE, pubKey);
		
		File original = new File(System.getProperty("user.home") + "//Desktop//OpenSSL.pdf.sign");
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(original))) {
			byte[] buffer = new byte[1024];
			int n; 
			while ((n = bis.read(buffer)) >0) 
				c.update(buffer, 0, n);
			System.out.println(c.getOutputSize(0));
//			c.doFinal(bis.readAllBytes());
			try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(System.getProperty("user.home") + "/Desktop/firmado.pdf.sign"))) {
				out.write(c.doFinal(bis.readAllBytes()));
			}
		}
		
	}
}

package T5_ProgSegura.Ejercicios.CifradoArchivos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Correccion_CifrarDescifrar {
	public static void main(String[] args) throws Exception {
		KeyStore ks = KeyStore.getInstance("pkcs12");
		char[] password = "TardeDAM123".toCharArray();
		ks.load(new FileInputStream("C:\\cygwin64\\home\\Tarde\\certs\\keystore.p12"), password);
//		ks.load(new FileInputStream("C:\\cygwin64\\home\\buru\\certs\\keystore.p12"), password);
		PrivateKey privKey = (PrivateKey) ks.getKey("miclave", "TardeDAM123".toCharArray()); 
		PublicKey pubKey = (PublicKey) ks.getCertificate("miclave").getPublicKey();
		
		cifrar(System.getProperty("user.home") + "\\Documents\\OpenSSL.pdf", pubKey);
		descifrar(System.getProperty("user.home") + "\\Documents\\OpenSSL.pdf.cifrado", privKey);
	}
	
	public static void cifrar(String path, Key key) {
		System.out.println("CIFRANDO...");
		Cipher cipher; 
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(path));
					BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(path + ".cifrado")))) {
				byte[] bloque = new byte[501];
				int n; 
				while ((n = in.read(bloque)) != -1) {
					out.write(cipher.doFinal(bloque, 0, n));
				}
			} catch (IOException | IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			e.printStackTrace();
		}
		System.out.println("CIFRADO COMPLETO");
	}
	
	public static void descifrar(String path, Key key) {
		System.out.println("DESCIFRANDO...");
		Cipher cipher; 
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(path));
					BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(path + ".pdf")))) {
				byte[] bloque = new byte[512];
				int n; 
				while ((n = in.read(bloque)) != -1) {
					out.write(cipher.doFinal(bloque, 0, n));
				}
			} catch (IOException | IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			e.printStackTrace();
		}
		System.out.println("DESCIFRADO COMPLETO");
	}
}

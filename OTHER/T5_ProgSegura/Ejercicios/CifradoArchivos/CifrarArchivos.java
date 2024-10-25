package T5_ProgSegura.Ejercicios.CifradoArchivos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class CifrarArchivos {
	public static void main(String[] args) throws IOException, KeyStoreException, NoSuchAlgorithmException,
			CertificateException, InvalidKeyException, SignatureException, UnrecoverableKeyException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		// CLASE
//		KeyStore ks = KeyStore.getInstance("pkcs12");
//		char[] password = "TardeDAM123".toCharArray();
//		ks.load(new FileInputStream("C:\\cygwin64\\home\\Tarde\\certs\\keystore.p12"), password);

		// CASA
		KeyStore ks = KeyStore.getInstance("pkcs12");
		char[] password = "TardeDAM123".toCharArray();
		ks.load(new FileInputStream("C:\\cygwin64\\home\\buru\\certs\\keystore.p12"), password);

		PrivateKey privKey = (PrivateKey) ks.getKey("miclave", password);
		PublicKey pubKey = (PublicKey) ks.getCertificate("miclave").getPublicKey();

		try (BufferedInputStream bis = new BufferedInputStream(
				new FileInputStream(System.getProperty("user.home") + "//Desktop//abc.txt")
//				CifrarArchivos.class.getResourceAsStream("/OpenSSL.pdf")
				);
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(System.getProperty("user.home") + "//Desktop//cifrado.pdf"))) {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			
			byte[] buffer = new byte[1024];
			int n; 
			while ((n = bis.read(buffer)) >0) 
				cipher.update(buffer, 0, n);
			
			cipher.doFinal();

			byte[] result = new byte[cipher.getBlockSize()];

			File original = new File(System.getProperty("user.home") + "//Desktop//unidad5.pdf");
			File cifrado = new File(System.getProperty("user.home") + "//Desktop//cifrado.pdf");
			try (FileInputStream fis = new FileInputStream(original);
					CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(cifrado), cipher)) {
				while ((n = fis.read(result)) != -1) {
					cos.write(result, 0, n);
				}
					
			}

		}

//		try (BufferedInputStream inF = new BufferedInputStream(
//				CifrarArchivos.class.getResourceAsStream("/OpenSSL.pdf"));
//				BufferedInputStream inS = new BufferedInputStream(
//						new FileInputStream(System.getProperty("user.home") + "/Desktop/OpenSSL.pdf.sign"));
//				BufferedOutputStream out = new BufferedOutputStream(
//						new FileOutputStream(System.getProperty("user.home") + "//Desktop//cifrado."))) {
////			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//			cipher.init(Cipher.ENCRYPT_MODE, privKey);
//
//			cipher.doFinal(inS.readAllBytes()); // FIXME¿¿¿
//
////			byte[] buffer = new byte[1024];
////			int n;
////			while ((n = inF.read(buffer)) > 0)
////				cipher.update(buffer, 0, n);
////			byte[] encrypted = cipher.doFinal();
//
////			out.write();

			System.out.println("aaaaaaaaaaaa");
//		}
	}
}

package T5_ProgSegura.Ejercicios.CifradoArchivos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import javax.crypto.Cipher;

import T5_ProgSegura.Ejercicios.VerificarFirma_Profe;

public class DescifrarArchivos {
	public static void main(String[] args) throws Exception {
		KeyStore ks = KeyStore.getInstance("pkcs12");
		char[] password = "TardeDAM123".toCharArray();
		ks.load(new FileInputStream("C:\\cygwin64\\home\\Tarde\\certs\\keystore.p12"), password);
//		ks.load(new FileInputStream("C:\\cygwin64\\home\\buru\\certs\\keystore.p12"), password);

		PrivateKey privKey = (PrivateKey) ks.getKey("miclave", "TardeDAM123".toCharArray());
		PublicKey pubKey = (PublicKey) ks.getCertificate("miclave").getPublicKey();
		// ^^ codigo cogido del ejemplo "KeyStore"

//		Cipher cipher = Cipher.getInstance("RSA/ECB/RSA/ECB/PKCS1Padding");
		Cipher c = Cipher.getInstance("RSA/ECB/NoPadding"); //de nuevo, da error si hay padding
		c.init(Cipher.DECRYPT_MODE, privKey);
		
//		try (BufferedInputStream inF = new BufferedInputStream(DescifrarArchivos.class.getResourceAsStream("/OpenSSL.pdf")); 
//				BufferedInputStream inS = new BufferedInputStream(new FileInputStream(System.getProperty("user.home") + "/Desktop/OpenSSL.pdf.sign"));
//				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(System.getProperty("user.home") + "/Desktop/OpenSSLdecrypt.pdf"))) {
//			byte[] buffer = new byte[1024];
//			int n; 
//			while ((n = inF.read(buffer)) > 0) 
//				c.update(buffer, 0, n);
////			byte[] decrypt = c.doFinal();
//			byte[] decrypt = c.doFinal(inF.readAllBytes());
//			
//			Signature sign = Signature.getInstance("SHA512withRSA");
//			sign.initVerify(pubKey);
//			buffer = new byte[1024];
//			n = (Integer) null;
//			while ((n = inF.read(buffer)) > 0)
//				sign.update(buffer, 0, n);
//			out.write(decrypt);
//			System.out.println(sign.verify(decrypt));
//		}
		
		File crypted = new File(System.getProperty("user.home") + "//Desktop//firmado.pdf.sign");
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(crypted))) {
			byte[] buffer = new byte[1024];
			int n;
			while ((n = bis.read(buffer)) > 0)
				c.update(buffer, 0, n);

			byte[] descriptado = c.doFinal();
			try (FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + "/Desktop/firmado.pdf")) {
				fos.write(descriptado);
			}
			try (BufferedInputStream inF = new BufferedInputStream(VerificarFirma_Profe.class.getResourceAsStream("/OpenSSL.pdf")); 
					BufferedInputStream inS = new BufferedInputStream(new FileInputStream(System.getProperty("user.home") + "/Desktop/firmado.pdf"))) {
				Signature sign = Signature.getInstance("SHA512withRSA");			
				sign.initVerify(pubKey);
				buffer = new byte[1024];
//				int n;
				while ((n = inF.read(buffer)) >0) 
					sign.update(buffer, 0, n); //CUIDADO!! si no se pone ", 0, n" NO FUNCIONA, retorna FALSE ((asumo que tiene que ver por como se hizo el update en el FirmarPDF
				System.out.println(sign.verify(inS.readAllBytes()));
			}
		}
	}
}

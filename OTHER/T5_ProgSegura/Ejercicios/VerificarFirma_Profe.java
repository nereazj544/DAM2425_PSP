package T5_ProgSegura.Ejercicios;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;

public class VerificarFirma_Profe {
	public static void main(String[] args) throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, InvalidKeyException, SignatureException {
		// CLASE 
//		KeyStore ks = KeyStore.getInstance("pkcs12");
//		char[] password = "TardeDAM123".toCharArray();
//		ks.load(new FileInputStream("C:\\cygwin64\\home\\Tarde\\certs\\keystore.p12"), password);
		
		//CASA
		KeyStore ks = KeyStore.getInstance("pkcs12");
		char[] password = "TardeDAM123".toCharArray();
//		ks.load(new FileInputStream("C:\\cygwin64\\home\\Tarde\\certs\\keystore.p12"), password);
		ks.load(new FileInputStream("C:\\cygwin64\\home\\buru\\certs\\keystore.p12"), password);
		
		PublicKey pubKey = (PublicKey) ks.getCertificate("miclave").getPublicKey(); 
		// ^^ ejemplo "KeyStore"
		
		// vv ejercicio
		try (BufferedInputStream inF = new BufferedInputStream(VerificarFirma_Profe.class.getResourceAsStream("/OpenSSL.pdf")); 
				BufferedInputStream inS = new BufferedInputStream(new FileInputStream(System.getProperty("user.home") + "/Desktop/OpenSSL.pdf.sign"))) {
			Signature sign = Signature.getInstance("SHA512withRSA");			
			sign.initVerify(pubKey);
			byte[] buffer = new byte[1024];
			int n; 
			while ((n = inF.read(buffer)) >0) 
				sign.update(buffer, 0, n); //CUIDADO!! si no se pone ", 0, n" NO FUNCIONA, retorna FALSE ((asumo que tiene que ver por como se hizo el update en el FirmarPDF
			System.out.println(sign.verify(inS.readAllBytes()));
		}
	}
}

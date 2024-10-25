package T5_ProgSegura.Ejercicios;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class FirmarPDF_Profe {
	public static void main(String[] args) throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
			FileNotFoundException, IOException, UnrecoverableKeyException, InvalidKeyException, SignatureException {
		KeyStore ks = KeyStore.getInstance("pkcs12");
		char[] password = "TardeDAM123".toCharArray();
		ks.load(new FileInputStream("C:\\cygwin64\\home\\Tarde\\certs\\keystore.p12"), password);
//		ks.load(new FileInputStream("C:\\cygwin64\\home\\buru\\certs\\keystore.p12"), password);

		PrivateKey privKey = (PrivateKey) ks.getKey("miclave", "TardeDAM123".toCharArray());
		// ^^ codigo cogido del ejemplo "KeyStore"

		// EJERCICIO
//		try (BufferedInputStream in = new BufferedInputStream(FirmarPDF_Profe.class.getResourceAsStream("/OpenSSL.pdf"))) {
		try (BufferedInputStream in = new BufferedInputStream(
				FirmarPDF_Profe.class.getResourceAsStream("/OpenSSL.pdf"))) {
			Signature sign = Signature.getInstance("SHA512withRSA");
			sign.initSign(privKey);
//			sign.update(in.readAllBytes()); //tecnicamente funciona pero "es muy bruto"
			byte[] buffer = new byte[1024];
			int n;
			while ((n = in.read(buffer)) > 0)
				sign.update(buffer, 0, n);
			byte[] signature = sign.sign();
			try (BufferedOutputStream out = new BufferedOutputStream(
					new FileOutputStream(System.getProperty("user.home") + "/Desktop/OpenSSL.pdf.sign"))) {
				out.write(signature);
			}
			System.out.println("Se ha generado la clave");
		}

	}
}

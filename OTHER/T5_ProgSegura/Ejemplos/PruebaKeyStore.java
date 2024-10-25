package T5_ProgSegura.Ejemplos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Arrays;

public class PruebaKeyStore {
	public static void main(String[] args) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException, UnrecoverableKeyException {
//		// CLASE 
//		KeyStore ks = KeyStore.getInstance("pkcs12");
//		char[] password = "TardeDAM123".toCharArray();
//		ks.load(new FileInputStream("C:\\cygwin64\\home\\Tarde\\certs\\keystore.p12"), password);
		
		//CASA
		KeyStore ks = KeyStore.getInstance("pkcs12");
		char[] password = "TardeDAM123".toCharArray();
		ks.load(new FileInputStream("C:\\cygwin64\\home\\buru\\certs\\keystore.p12"), password);
		
		System.out.println("CLAVE PRIVADA: ");
		PrivateKey privKey = (PrivateKey) ks.getKey("miclave", "TardeDAM123".toCharArray()); //miclave es el alias de la clave que puse yo sos
		System.out.println(privKey.getAlgorithm());
		System.out.println(privKey.getFormat());
		System.out.println(privKey.getEncoded());
		System.out.println(privKey.getClass());
		
		System.out.println("\nCERTIFICADO: ");
		Certificate miCert = ks.getCertificate("miclave");
		System.out.println(miCert.getType());
		System.out.println(Arrays.toString(miCert.getEncoded()));
		
		System.out.println("\nCLAVE PUBLICA: ");
		PublicKey pubKey = (PublicKey) miCert.getPublicKey(); 
		System.out.println(pubKey.getAlgorithm());
		System.out.println(pubKey.getFormat());
		System.out.println(pubKey.getEncoded());
		System.out.println(pubKey.getClass());
	}
}

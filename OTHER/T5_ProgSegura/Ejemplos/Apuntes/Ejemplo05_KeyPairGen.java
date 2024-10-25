package T5_ProgSegura.Ejemplos.Apuntes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.print.attribute.standard.Sides;

public class Ejemplo05_KeyPairGen {
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		keyGen.initialize(1024, random); // tamaño clave tiene que ser multiplo de 64 entre 512 y 1024
		KeyPair par = keyGen.generateKeyPair();
		PrivateKey privada = par.getPrivate();
		PublicKey publica = par.getPublic();
		
		//FIRMA
		Signature signature = Signature.getInstance("SHA256withDSA");
		signature.initSign(privada, random);
		signature.update("texto de prueba".getBytes());
		byte[] firma = signature.sign();
		
		//VERIFICACION
		signature.initVerify(publica);
		signature.update("texto de prueba".getBytes());
		System.out.println(signature.verify(firma) ? "VERIFICADO" : "NO VERIFICADO");
		
		almacenaryrecoger(par); //no se si es parte del ejemplo o no pero lo pongo aqui just in case
	}

	private static void almacenaryrecoger(KeyPair par) throws NoSuchAlgorithmException {
		// Almacenar bytes clave en fichero
		File file = new File(System.getProperty("user.home") + "/miclave.priv");
		try (FileOutputStream out = new FileOutputStream("miclave.priv")) {
			out.write(par.getPrivate().getEncoded());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		file = new File(System.getProperty("user.home") + "/miclave.pub");
		try (FileOutputStream out = new FileOutputStream("miclave.pub")) {
			out.write(par.getPublic().getEncoded());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// recuperar clave privada guardada en un fichero
		KeyFactory factory = KeyFactory.getInstance("DSA");
		File filePrivada = new File(System.getProperty("user.home") + "/miclave.priv");
		try (FileInputStream in = new FileInputStream(filePrivada)) {
			byte[] bufferPriv = new byte[in.available()];
			in.read(bufferPriv);
			PKCS8EncodedKeySpec clavePrivadaSpec = new PKCS8EncodedKeySpec(bufferPriv);
			PrivateKey priv = factory.generatePrivate(clavePrivadaSpec);
			System.out.println("Clave privada: " + priv.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		// recuperar clave publica guardada en un fichero
		File filePublica = new File(System.getProperty("user.home") + "/miclave.pub");
		try (FileInputStream in = new FileInputStream(filePublica)) {
			byte[] bufferPub = new byte[in.available()];
			in.read(bufferPub);
			X509EncodedKeySpec clavePublicaSpec = new X509EncodedKeySpec(bufferPub);
			PublicKey pub = factory.generatePublic(clavePublicaSpec);
			System.out.println("Clave privada: " + pub.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
	}
}

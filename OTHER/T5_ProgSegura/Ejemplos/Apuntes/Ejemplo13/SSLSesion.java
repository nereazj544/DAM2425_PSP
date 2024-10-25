package T5_ProgSegura.Ejemplos.Apuntes.Ejemplo13;

import java.math.BigInteger;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

public class SSLSesion {
	public static void main(String[] args) throws SSLPeerUnverifiedException {
		SSLSocket socket = null;
		SSLSession session = socket.getSession();
		System.out.println("Host: " + session.getPeerHost());
		System.out.println("Cifrado: " + session.getCipherSuite());
		System.out.println("Protocolo: " + session.getProtocol());
		System.out.println("IDentificador:" + new BigInteger(session.getId()));
		System.out.println("Creación de la sesión: " + session.getCreationTime());
		X509Certificate certificate = (X509Certificate) session.getPeerCertificates()[0];
		System.out.println("Propietario: " + certificate.getSubjectDN());
		System.out.println("Algoritmo: " + certificate.getSigAlgName());
		System.out.println("Tipo: " + certificate.getType());
		System.out.println("Emisor: " + certificate.getIssuerDN());
		System.out.println("Número Serie: " + certificate.getSerialNumber());
	}
}

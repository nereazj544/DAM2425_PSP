package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ServidorAgenda.Key;

import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.util.*;

// import org.bouncycastle.x509.X509V3CertificateGenerator;

/*
 * 
 * public class ServidorKey {
 * private static final String KS_Ruta =
 * "src\\main\\java\\fp\\dam\\psp\\CLASS\\EvSegunda\\ActividadesdeEntrega\\ServidorAgenda\\Key\\ServisorKey.p12";
 * private static final String KS_Contra = "napoles";
 * private static final String CERT_Alias = "servidor";
 * 
 * // ----
 * 
 * public static void iniciarS() {
 * try {
 * File ksFile = new File(KS_Ruta);
 * 
 * if (!ksFile.exists()) {
 * System.out.println("> No se ha encontrado. Creando...");
 * crearKey();
 * }
 * 
 * } catch (Exception e) {
 * // TODO: handle exception
 * }
 * }
 * 
 * private static void crearKey() throws Exception {
 * KeyPairGenerator keyPaiGen = KeyPairGenerator.getInstance("RSA");
 * keyPaiGen.initialize(2048);
 * KeyPair keyPai = keyPaiGen.generateKeyPair();
 * 
 * X509Certificate cert = generarCert(keyPai);
 * 
 * KeyStore ks = KeyStore.getInstance("PKCS12");
 * ks.load(null, null);
 * 
 * Certificate[] certChain = new Certificate[1];
 * certChain[0] = cert;
 * ks.setKeyEntry(CERT_Alias, keyPai.getPrivate(), KS_Contra.toCharArray(),
 * certChain);
 * 
 * try (FileOutputStream fos = new FileOutputStream(KS_Ruta)) {
 * ks.store(fos, KS_Contra.toCharArray());
 * System.out.println("> KeyStore creado, en: " + KS_Ruta);
 * }
 * 
 * }
 * 
 * private static X509Certificate generarCert(KeyPair keyPai) throws Exception{
 * X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();
 * certGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));
 * certGen.setSubjectDN(new X509Name("CN=Servidor"));
 * certGen.setIssuerDN(new X509Name("CN=Servidor"));
 * certGen.setNotBefore(new Date(System.currentTimeMillis() - 1000L * 60 * 60 *
 * 24 * 30));
 * certGen.setNotAfter(new Date(System.currentTimeMillis() + (1000L * 60 * 60 *
 * 24 * 365 * 10)));
 * certGen.setPublicKey(keyPai.getPublic());
 * certGen.setSignatureAlgorithm("SHA256WithRSAEncryption");
 * 
 * return certGen.generate(keyPai.getPrivate());
 * }
 * 
 * }
 * 
 */
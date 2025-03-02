package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ServidorAgenda.Server;

import java.io.*;
import java.net.*;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.sql.Date;
import java.util.concurrent.*;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

//TODO MIRAR PORQUE NO CARRULA

public class Server {

    // CLAVES
    

    private static PrivateKey privateKey;
    private static X509Certificate cert;

    public static void main(String[] args) {
        try (ServerSocket sSck = new ServerSocket(6000)) {

            // Agenda.Claves();
            // cargarClaves();

            ExecutorService ExSer = Executors.newFixedThreadPool(100);
            System.out.println(" ");
            System.out.println(". . . Servidor iniciando . . . ");
            System.out.println(". . . Puerto en escucha: 6000 . . . ");

            while (true) {
                Socket sck = sSck.accept();
                ExSer.execute(new Agenda(sck, privateKey, cert));
            }

        } catch (Exception e) {
            e.getMessage();
        }
    }

    // private static void cargarClaves() throws Exception {
    //     KeyStore ks = KeyStore.getInstance("PKCS12");
    //     File KY_arch = new File(KS_Ruta);

    //     if (!KY_arch.exists()) {
    //         System.out.println("Error: No se ha encontrado el archivo de claves");
    //         System.out.println("> Creando archivo de claves");
    //         ks.load(null, KS_pass.toCharArray());

    //         KeyPair kyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
    //         X509Certificate cert = generarCertificado(kyPair);

    //         ks.setKeyEntry(KS_Alias, privateKey, KS_pass.toCharArray(), new Certificate[] { cert });

    //         try (FileOutputStream fos = new FileOutputStream(KY_arch)) {
    //             ks.store(fos, KS_pass.toCharArray());
    //         }

    //     } else {
    //         System.out.println("> Cargadon clave existente");
    //         ks.load(new FileInputStream(KY_arch), KS_pass.toCharArray());
    //     }

    //     privateKey = (PrivateKey) ks.getKey(KS_Alias, KS_pass.toCharArray());
    //     cert = (X509Certificate) ks.getCertificate(KS_Alias);
    // }

    // static {
    //     Security.addProvider(new BouncyCastleProvider());
    // }

    // private static X509Certificate generarCertificado(KeyPair kyPair) throws Exception {
        // long ahora = System.currentTimeMillis();
        // Date fechaInicio = new Date(ahora);
        // Date fechaFin = new Date(ahora + 365 * 24 * 60 * 60 * 1000);

        // X500Name nombre = new X500NameBuilder(BCStyle.INSTANCE)
        //         .addRDN(BCStyle.CN, "Servidor")
        //         .addRDN(BCStyle.O, "PSP")
        //         .addRDN(BCStyle.C, "ES")
        //         .build();


                


    // }
}

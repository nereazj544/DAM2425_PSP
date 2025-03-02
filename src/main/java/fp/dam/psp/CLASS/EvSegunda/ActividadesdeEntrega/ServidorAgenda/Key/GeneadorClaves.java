package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ServidorAgenda.Key;

import java.security.*;

import java.security.cert.*;


public class GeneadorClaves {

    // TODO Servidor
    public static void crearServerKeyStore(String keyPath, String contra) throws Exception {
        crearKeyStore(keyPath, contra, "server", "CN= ServidorA");
    }

    // TODO Cliente



    // TODO Generico
    private static void crearKeyStore(String keyPath, String contra, String alias, String nombre) throws Exception{
        KeyPair kpair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
        X509Certificate cert = generaCerti(kpair, nombre);
        
                KeyStore ks = KeyStore.getInstance("PKCS12");
        
            }
        
            private static X509Certificate generaCerti(KeyPair kpair, String nombre) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'generaCerti'");
            }
}
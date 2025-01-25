package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Viernes24.CertificacionDeClaves;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

public class Claves {
    public static void main(String[] args) {
        PrivateKey pKey;

        try (DataInputStream in = new DataInputStream(new FileInputStream("src\\main\\java\\fp\\dam\\psp\\CLASS\\EvSegunda\\Tema5_ProgSegura\\Viernes24\\CertificacionDeClaves\\certs\\rsa_priv.p8"))) {
            byte[] by = new byte[in.available()];
            in.read(by);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(by);
            KeyFactory kf = KeyFactory.getInstance("rsa");
            pKey = kf.generatePrivate(spec);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}

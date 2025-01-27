package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.EjemplosU5;

import javax.crypto.*;

public class Ejemplo6 {
    public static void main(String[] args) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(256);
        SecretKey key = kg.generateKey();

        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");

        c.init(Cipher.ENCRYPT_MODE, key);

        byte[] tc = c.doFinal("TOP SECRET".getBytes());
        System.out.println("Texto Cifrado: " + new String(tc));

        c.init(Cipher.DECRYPT_MODE, key);
        System.out.println("Texto original: " + new String(c.doFinal(tc)));

    }

}

package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.EjemplosU5;

import javax.crypto.*;
import javax.crypto.spec.*;

public class Ejemplo7 {
    public static void main(String[] args) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(256);
        SecretKey clave = kg.generateKey();

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, clave);
        IvParameterSpec iv = new IvParameterSpec(cipher.getIV());

        byte[] textoCifrado = cipher.doFinal("TOP SECRET".getBytes());
        System.out.println("Texto cifrado: " + new String(textoCifrado));

        cipher.init(Cipher.DECRYPT_MODE, clave, iv);
        System.out.println("Texto original: " + new String(cipher.doFinal(textoCifrado)));
    }
}

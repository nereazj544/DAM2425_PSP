package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ServidorAgenda_V1.server;

//! --- IMPORTS ---
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import java.net.*;

import java.security.*;
import javax.crypto.*;
import java.security.cert.Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

//! --- END IMPORTS ---

public class AgendaTask implements Runnable {
    // TODO VARIABLES SERVER
    private final Socket sck;
    private static final Map<String, Set<String>> contactos = new ConcurrentHashMap<>();

    // TODO CLAVES
    private SecretKey KY_scret;
    private static final String KYSV_Path = "src\\main\\java\\fp\\dam\\psp\\CLASS\\EvSegunda\\ActividadesdeEntrega\\ServidorAgenda_V1\\Key\\servidor.p12";
    private static final String KYSV_Pass = "servidor"; // ! Contraseña

    // ! ---- END VARIABLES SERVER ----

    // TODO: Constructor
    public AgendaTask(Socket sck) {
        this.sck = sck;
    }
    // ! ---- END CONSTRUCTOR ----

    // TODO: RUn
    @Override
    public void run() {
        try (sck;
                DataInputStream in = new DataInputStream(sck.getInputStream());
                DataOutputStream out = new DataOutputStream(sck.getOutputStream())) {

            KeyStore ks = KeyStore.getInstance("PKCS12");

            try (FileInputStream fl_S = new FileInputStream(KYSV_Path)) {
                ks.load(fl_S, KYSV_Pass.toCharArray());
            }

            PrivateKey KY_privSV = (PrivateKey) ks.getKey("servidor", KYSV_Pass.toCharArray());
            Certificate cert = ks.getCertificate("servidor");
            PublicKey KY_pubCT = getCTPubKey(in);

            KY_scret = AesKey();

            enviarClave(out, KY_pubCT, KY_scret);

            while (true) {
                String encryptedRequest = in.readUTF();
                String request = decryptAES(encryptedRequest, KY_scret);
                String response = handleRequest(request);
                String signedResponse = signResponse(response, KY_privSV);
                String encryptedResponse = encryptAES(signedResponse, KY_scret);
                out.writeUTF(encryptedResponse);

            }

        } catch (Exception e) {
            // TODO: handle exception
        }

        // ! --- END RUN ---
    }

    private void enviarClave(DataOutputStream out, PublicKey clientPublicKey, SecretKey aesKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, clientPublicKey);
        byte[] encryptedAESKey = cipher.doFinal(aesKey.getEncoded());
        out.writeInt(encryptedAESKey.length);
        out.write(encryptedAESKey);
    }

    private SecretKey AesKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            return keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: Obtener clave pub del cliente
    private PublicKey getCTPubKey(DataInputStream in)
            throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        byte[] keyBytes = new byte[in.readInt()];
        in.readFully(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes));
    }

    private String encryptAES(String data, SecretKey aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }

    private String decryptAES(String encryptedData, SecretKey aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
    }

    private String signResponse(String data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        return data + "\\nFirma:" + Base64.getEncoder().encodeToString(signature.sign());
    }

    private String handleRequest(String request) {
        String[] parts = request.split(":");
        if (parts.length < 2)
            return "ERR03\\n" + request;

        switch (parts[0]) {
            case "nombre":
                return addContact(parts[1], parts[2]);
            case "buscar":
                return searchContact(parts[1]);
            case "eliminar":
                return deleteContact(parts[1]);
            case "contactos":
                return listContacts();
            default:
                return "ERR03\\n" + request;
        }
    }

    private synchronized String addContact(String name, String phone) {
        contactos.putIfAbsent(name, new HashSet<>());
        if (!contactos.get(name).add(phone)) {
            return "ERR01";
        }
        return "OK";
    }

    private synchronized String searchContact(String name) {
        if (!contactos.containsKey(name)) {
            return "ERR02";
        }
        return "OK\\n" + String.join("\\n", contactos.get(name));
    }

    private synchronized String deleteContact(String name) {
        if (contactos.remove(name) == null) {
            return "ERR02";
        }
        return "OK";
    }

    private synchronized String listContacts() {
        if (contactos.isEmpty()) {
            return "ERR02";
        }
        StringBuilder result = new StringBuilder("OK\\n");
        contactos.keySet().stream().sorted().forEach(name -> result.append(name).append("\\n"));
        return result.toString();
    }
}
// ! -- END CLAS --

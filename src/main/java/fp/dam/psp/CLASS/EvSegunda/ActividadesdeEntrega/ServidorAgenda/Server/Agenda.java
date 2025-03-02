package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ServidorAgenda.Server;

import java.io.*;
import java.net.*;

import java.security.*;
import java.security.spec.*;

import java.util.*;
import java.util.concurrent.*;

//TODO MIRAR PORQUE NO CARRULA


public class Agenda implements Runnable {
    private final Socket sck;
    private static final Map<String, Set<String>> contactos = new ConcurrentHashMap<>();
    private static PrivateKey pvKey; // Clave privada - Servidor
    private static PublicKey pubKey; // Clave publica - Cliente

    public Agenda(Socket sck) {
        this.sck = sck;
    }

    @Override
    public void run() {
        try (sck;
                DataInputStream in = new DataInputStream(sck.getInputStream());
                DataOutputStream out = new DataOutputStream(sck.getOutputStream())) {

            int keyLen = in.readInt();
            byte[] keyClient = new byte[keyLen];
            in.readFully(keyClient);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(keyClient);
            pubKey = keyFactory.generatePublic(pubKeySpec);
            out.writeUTF("> Recibida clave publica del cliente");

            String m = in.readUTF();
            out.writeUTF("> Mensaje recibido: " + m);

            String r = proMen(m);
            String firma = firmarMen(r);
            out.writeUTF(r);
            out.writeUTF(firma);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private String firmarMen(String mensjae) throws Exception {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(pvKey);
        sign.update(mensjae.getBytes());
        return Base64.getEncoder().encodeToString(sign.sign());
    }

    private String proMen(String m) {
        String[] partes = m.split(":", 2);
        if (partes.length < 2 && !m.equals("contactos")) {
            return "ERROR3\n" + m + " no es un comando valido";
        }

        String cmd = partes[0].toLowerCase();
        String arg = "";
        if (partes.length > 1) {
            arg = partes[1].trim();
        }

        switch (cmd) {
            case "nombre":
                return añadir(arg);

            case "buscar":
                return buscar(arg);

            case "eliminar":
                return elimar(arg);

            case "contactos":
                return listar(arg);

            default:
                return "ERROR2\n " + m + " no es un comando valido";
        }

    }

    private String añadir(String arg) {
        String[] info = arg.split(" ", 2);
        if (info.length < 2 || !info[1].matches("\\d+")) {
            return "ERROR3\n" + arg + " no es un contacto valido";
        }

        String n = info[0];
        String t = info[1];
        contactos.putIfAbsent(n, ConcurrentHashMap.newKeySet());
        if (contactos.get(n).contains(t)) {
            return "ERROR1";
        }
        contactos.get(n).add(t);
        return "OK";
    }

    private String buscar(String arg) {
        if (contactos.containsKey(arg)) {
            return "ERROR 2\n No hay contactos";
        }
        return "OK" + String.join("\n", contactos.get(arg));
    }

    private String elimar(String arg) {
        if (contactos.remove(arg) == null) {
            return "ERROR 2\n No hay contactos";
        }
        return "OK";
    }

    private String listar(String arg) {
        if (contactos.isEmpty()) {
            return "ERROR 2\n No hay contactos";
        }
        List<String> lista = new ArrayList<>();
        Collections.sort(lista);
        return "OK" + String.join("\n", lista);
    }

    // public static void Claves() throws Exception{
    //     KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    //     keyGen.initialize(2048);
    //     KeyPair keyPair = keyGen.generateKeyPair();
    //     pvKey = keyPair.getPrivate();

    //     try (FileOutputStream fos = new FileOutputStream("public.key")) {
    //         fos.write(keyPair.getPublic().getEncoded());

    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //     }
    // }
}

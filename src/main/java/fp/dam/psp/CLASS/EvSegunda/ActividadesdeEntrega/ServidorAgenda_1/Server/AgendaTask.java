package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ServidorAgenda_1.Server;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AgendaTask implements Runnable {
    private Socket sck;
    private PrivateKey KY_priv;
    private PublicKey KY_pub;
    private DataInputStream in;
    private DataOutputStream out;

    private SecretKey KY_Simetrica;

    // TODO _ Almacenar contactos
    private static ConcurrentHashMap<String, Set<String>> agenda = new ConcurrentHashMap<>();

    public AgendaTask(Socket sck, PrivateKey kY_priv, PublicKey kY_pub) {
        this.sck = sck;
        KY_priv = kY_priv;
        KY_pub = kY_pub;
    }

    @Override
    public void run() {
        try {
            in = new DataInputStream(sck.getInputStream());
            out = new DataOutputStream(sck.getOutputStream());

            if (Comunicacion()) {
                procesarPeticinoes();
            }

        } catch (Exception e) {
            System.err.println("> Error en la comunicacion con el cliente: " + e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    private boolean Comunicacion() {
        try {

            byte[] KY_PubByte = KY_pub.getEncoded();
            out.writeInt(KY_PubByte.length);
            out.write(KY_PubByte);
            out.flush();

            int lon = in.readInt();
            byte[] KY_Cifrada = new byte[lon];
            in.readFully(KY_Cifrada);

            Cipher RSA_Des = Cipher.getInstance("RSA");
            RSA_Des.init(Cipher.DECRYPT_MODE, KY_priv);
            byte[] KY_Des = RSA_Des.doFinal(KY_Cifrada);

            KY_Simetrica = new SecretKeySpec(KY_Des, "AES");
            System.out
                    .println("> Comunicacion segura establecida con cliente: " + sck.getInetAddress().getHostAddress());
            return true;

        } catch (Exception e) {
            System.out.println("> Error al establecer la comunicacion segura: " + e.getMessage());
            return false;
        }
    }

    private void procesarPeticinoes() {
        boolean con = true;

        while (con) {
            try {
                int long_MSG = in.readInt();
                byte[] MSG_cifrado = new byte[long_MSG];
                in.readFully(MSG_cifrado);

                Cipher AES_Des = Cipher.getInstance("AES");
                AES_Des.init(Cipher.DECRYPT_MODE, KY_Simetrica);

                byte[] MSG_Des = AES_Des.doFinal(MSG_cifrado);
                String peticion = new String(MSG_Des);

                if (peticion.isEmpty()) {
                    con = false;
                    continue;
                }

                String respuesta = procesarPeticinoes(peticion);

                Cipher AES_Cifrar = Cipher.getInstance("AES");
                AES_Cifrar.init(Cipher.ENCRYPT_MODE, KY_Simetrica);
                byte[] respuestaCifrada = AES_Cifrar.doFinal(respuesta.getBytes());

                out.writeInt(respuesta.length());
                out.write(respuestaCifrada);
                out.flush();

            } catch (Exception e) {
                con = false;
            }
        }

    }

    private String procesarPeticinoes(String peticion) {
        System.out.println("> Peticion recibida: " + peticion);

        if (peticion.toLowerCase().matches("^[^:]+:\\s*\\d+$")) {
            // TODO_ AÑADIR CONTACTO
            String[] parts = peticion.split(":");
            String nombre = parts[0].trim();
            String telefono = parts[1].trim();

            return agregarContacto(nombre, telefono);

        } else if (peticion.toLowerCase().matches("^buscar:[^:]+$")) {
            // TODO_ Buscar contacto
            String nombre = peticion.substring(7).trim();
            return buscarContacto(nombre);
        } else if (peticion.toLowerCase().matches("^eliminar:[^:]+$")) {
            // TODO_ Eliminar contacto
            String nombre = peticion.substring(9).trim();
            return eliminarContacto(nombre);
        } else if (peticion.toLowerCase().equals("contactos")) {
            // TODO_ Listar contactos
            return listarContactos();
        } else {
            // TODO_ Error de sintaxis
            return "ERR03\n" + peticion + "\n^";
        }

    }

    private String agregarContacto(String nombre, String telefono) {
        agenda.putIfAbsent(nombre, ConcurrentHashMap.newKeySet());
        Set<String> telefonos = agenda.get(nombre);
        if (telefonos.contains(telefono)) {
            return "ERROR01";
        } else {
            telefonos.add(telefono);
            return "OK";
        }

    }

    private String buscarContacto(String nombre) {
        if (agenda.containsKey(nombre)) {
            StringBuilder response = new StringBuilder("OK\n");

            Set<String> telSet = agenda.get(nombre);
            for (String telefono : telSet) {
                response.append(telefono).append("\n");
            }
            return response.toString().trim();
        } else {
            return "ERROR02";
        }
    }

    private String listarContactos() {
        StringBuilder response = new StringBuilder("OK\n");

        // Obtener lista ordenada de nombres
        List<String> nombres = new ArrayList<>(agenda.keySet());
        Collections.sort(nombres);

        // Añadir cada nombre
        for (String nombre : nombres) {
            response.append(nombre).append("\n");
        }

        return response.toString().trim();

    }

    private String eliminarContacto(String nombre) {
        if (agenda.containsKey(nombre)) {
            agenda.remove(nombre);
            return "OK";
        } else {
            return "ERR02";
        }
    }

    private void cerrarConexion() {
        try {
            if (in != null)
                in.close();
            if (out != null)
                out.close();
            if (sck != null && !sck.isClosed())
                sck.close();
            System.out.println("Conexión cerrada con cliente: " + sck.getInetAddress().getHostAddress());
        } catch (IOException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

}

package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ServidorAgenda_1.Client;

//! Import 
import java.net.*;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.io.*;

import java.awt.*;
import java.awt.event.*;

import javax.crypto.*;
import javax.swing.*;
import javax.swing.text.*;

public class Client extends JFrame {

    // TODO
    private Socket sck;
    private DataInputStream in;
    private DataOutputStream out;

    // TODO_ CLAVES
    private PublicKey pubkey;
    private PrivateKey pvkey;
    private SecretKey KY_Simetrica;

    // ! Ruta de la clave
    private static final String KY_Path = "src\\main\\java\\fp\\dam\\psp\\CLASS\\EvSegunda\\ActividadesdeEntrega\\ServidorAgenda_1\\Key\\cliente.p12";
    private static final String KY_Pass = "cliente"; // ! Contraseña

    // TODO COMPONENTES Swing
    private static final long serialVersionUID = 1L;

    // Cliente
    private JTextArea ClienteText = new JTextArea();

    // Servidor
    private JTextArea ServerText = new JTextArea();

    // Nota
    JTextPane nota1 = new JTextPane();
    JTextArea mensaje = new JTextArea();

    // Botones
    private JButton enviar = new JButton("ENVIAR");

    // TODO Configuracion de la aplicacion
    public Client() {
        super("Agenda Signature");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JScrollPane jp;

        // TODO: CLIENTE
        JLabel Cliente = new JLabel("Cliente");
        Cliente.setFont(new Font("Console", Font.BOLD, 20));
        ClienteText.setLineWrap(true);
        Cliente.setBounds(45, 50, 200, 30);
        add(Cliente);
        ClienteText.setEditable(true);

        jp = new JScrollPane(ClienteText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jp.setBounds(50, 78, 200, 200);
        add(jp);

        // TODO: Servidor
        JLabel Servidor = new JLabel("Servidor");
        Servidor.setFont(new Font("Console", Font.BOLD, 20));
        Servidor.setBounds(350, 50, 200, 30);
        add(Servidor);

        ServerText.setLineWrap(true);
        ServerText.setEditable(false);
        jp = new JScrollPane(ServerText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jp.setBounds(350, 78, 200, 200);
        add(jp);

        // TODO: Boton
        enviar.setBounds(240, 336, 119, 36);
        enviar.addActionListener(this::enviar);
        add(enviar);

        // TODO: Mensje error
        mensaje.setLineWrap(true);
        mensaje.setEditable(false);
        jp = new JScrollPane(mensaje, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jp.setBounds(50, 285, 200, 40);
        add(jp);

        // TODO: TEXTO NOTA
        nota1.setText(
                "Introduce tu petición según el formato:\n- nombre: teléfono (añadir contacto), buscar:nombre (buscar contacto),eliminar:nombre (eliminar), contactos (listar todos)");
        nota1.setFont(new Font("Consolas", Font.BOLD, 9));
        nota1.setBackground(Color.LIGHT_GRAY);
        nota1.setEditable(false);

        // TODO: CENTRAR TEXTO DE NOTA:
        StyledDocument doc = nota1.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        nota1.setBounds(50, 5, 500, 40);
        add(nota1);

        setLocationRelativeTo(null);

        // TODO: Configuracion con el servidor;
        ServidorConexion();

    }

    // TODO Server Configuracion
    private void ServidorConexion() {
        try {
            sck = new Socket("localhost", 6000);
            in = new DataInputStream(sck.getInputStream());
            out = new DataOutputStream(sck.getOutputStream());

            // Recibir clave pública del servidor
            int longitudClave = in.readInt();
            byte[] clavePublicaBytes = new byte[longitudClave];
            in.readFully(clavePublicaBytes);

            // Convertir bytes a objeto PublicKey
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            pubkey = keyFactory.generatePublic(new X509EncodedKeySpec(clavePublicaBytes));

            // Generar clave simétrica AES
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            KY_Simetrica = keyGen.generateKey();

            // Cifrar la clave simétrica con la clave pública del servidor
            Cipher rsaCifrar = Cipher.getInstance("RSA");
            rsaCifrar.init(Cipher.ENCRYPT_MODE, pubkey);
            byte[] claveCifrada = rsaCifrar.doFinal(KY_Simetrica.getEncoded());

            // Enviar la clave simétrica cifrada al servidor
            out.writeInt(claveCifrada.length);
            out.write(claveCifrada);
            out.flush();

            ServerText.append("Conexión segura establecida con el servidor\n");

        } catch (Exception e) {
            mostrarError("Error al establecer conexión segura: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void enviar(ActionEvent e) {
        try {
            String mensaje = ClienteText.getText().trim();

            // Si el mensaje está vacío, no hacer nada
            if (mensaje.isEmpty()) {
                mostrarError("Introduce un mensaje válido");
                return;
            }

            // Cifrar mensaje con la clave simétrica
            Cipher aesCifrar = Cipher.getInstance("AES");
            aesCifrar.init(Cipher.ENCRYPT_MODE, KY_Simetrica);
            byte[] mensajeCifrado = aesCifrar.doFinal(mensaje.getBytes());

            // Enviar mensaje cifrado
            out.writeInt(mensajeCifrado.length);
            out.write(mensajeCifrado);
            out.flush();

            ServerText.append("> Mensaje enviado: " + mensaje + "\n");

            // Recibir respuesta cifrada
            int longitudRespuesta = in.readInt();
            byte[] respuestaCifrada = new byte[longitudRespuesta];
            in.readFully(respuestaCifrada);

            // Descifrar respuesta
            Cipher aesDescifrar = Cipher.getInstance("AES");
            aesDescifrar.init(Cipher.DECRYPT_MODE, KY_Simetrica);
            byte[] respuestaDescifrada = aesDescifrar.doFinal(respuestaCifrada);
            String respuesta = new String(respuestaDescifrada);

            ServerText.append("> Respuesta del servidor: " + respuesta + "\n");
            ClienteText.setText(""); // Limpiar campo de texto

        } catch (Exception ex) {
            mostrarError("Error al enviar/recibir: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void cerrarConexion() {
        try {
            // Enviar mensaje vacío para indicar fin de comunicación
            Cipher aesCifrar = Cipher.getInstance("AES");
            aesCifrar.init(Cipher.ENCRYPT_MODE, KY_Simetrica);
            byte[] mensajeCifrado = aesCifrar.doFinal("".getBytes());

            out.writeInt(mensajeCifrado.length);
            out.write(mensajeCifrado);
            out.flush();

            if (in != null)
                in.close();
            if (out != null)
                out.close();
            if (sck != null)
                sck.close();
        } catch (Exception e) {
            mostrarError("Error al cerrar conexión: " + e.getMessage());
        }
    }

    // Métodos para gestionar el KeyStore
    private void crearKeyStore() {
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(null, KY_Pass.toCharArray());

            // Generar par de claves RSA
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair keyPair = keyGen.generateKeyPair();

            // Guardar clave privada en el KeyStore
            KeyStore.PrivateKeyEntry privateKeyEntry = new KeyStore.PrivateKeyEntry(
                    keyPair.getPrivate(),
                    new java.security.cert.Certificate[] {
                            generarCertificadoAutofirmado(keyPair, "CN=Cliente")
                    });

            keyStore.setEntry("clientkey", privateKeyEntry,
                    new KeyStore.PasswordProtection(KY_Pass.toCharArray()));

            // Guardar KeyStore en archivo
            try (FileOutputStream fos = new FileOutputStream(KY_Path)) {
                keyStore.store(fos, KY_Pass.toCharArray());
            }

        } catch (Exception e) {
            mostrarError("Error al crear KeyStore: " + e.getMessage());
        }
    }

    private KeyStore cargarKeyStore() {
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            File keyStoreFile = new File(KY_Path);

            if (!keyStoreFile.exists()) {
                crearKeyStore();
            }

            try (FileInputStream fis = new FileInputStream(KY_Path)) {
                keyStore.load(fis, KY_Pass.toCharArray());
            }

            return keyStore;
        } catch (Exception e) {
            mostrarError("Error al cargar KeyStore: " + e.getMessage());
            return null;
        }
    }

    // TODO Mensjae de error
    private void mostrarError(String msg) {
        mensaje.setText(msg + "\n");
        mensaje.setForeground(Color.RED);
    }

    // TODO Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Client().setVisible(true));
    }

}

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
        enviar.addActionListener(this::enviar);
        enviar.setBounds(240, 336, 119, 36);
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

            int longitudClave = in.readInt();
            byte[] clavePublicaBytes = new byte[longitudClave];
            in.readFully(clavePublicaBytes);
            
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            pubkey = keyFactory.generatePublic(new X509EncodedKeySpec(clavePublicaBytes));

            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            KY_Simetrica = keyGen.generateKey();

            Cipher rsaCifrar = Cipher.getInstance("RSA");
            rsaCifrar.init(Cipher.ENCRYPT_MODE, pubkey);
            byte[] claveCifrada = rsaCifrar.doFinal(KY_Simetrica.getEncoded());

            out.writeInt(claveCifrada.length);
            out.write(claveCifrada);
            out.flush();

            ServerText.append("Conexión segura establecida con el servidor\n");
        } catch (Exception e) {
            mostrarError("Error al establecer conexión segura: " + e.getMessage());
        }
    }

    private void enviar(ActionEvent e) {
        try {
            String mensaje = ClienteText.getText().trim();
            if (mensaje.isEmpty()) {
                mostrarError("Introduce un mensaje válido");
                return;
            }

            Cipher aesCifrar = Cipher.getInstance("AES");
            aesCifrar.init(Cipher.ENCRYPT_MODE, KY_Simetrica);
            byte[] mensajeCifrado = aesCifrar.doFinal(mensaje.getBytes());

            out.writeInt(mensajeCifrado.length);
            out.write(mensajeCifrado);
            out.flush();

            ServerText.append("> Mensaje enviado: " + mensaje + "\n");

            int longitudRespuesta = in.readInt();
            byte[] respuestaCifrada = new byte[longitudRespuesta];
            in.readFully(respuestaCifrada);

            Cipher aesDescifrar = Cipher.getInstance("AES");
            aesDescifrar.init(Cipher.DECRYPT_MODE, KY_Simetrica);
            byte[] respuestaDescifrada = aesDescifrar.doFinal(respuestaCifrada);
            String respuesta = new String(respuestaDescifrada);

            ServerText.append("> Respuesta del servidor: " + respuesta + "\n");
            ClienteText.setText("");
        } catch (Exception ex) {
            mostrarError("Error al enviar/recibir: " + ex.getMessage());
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

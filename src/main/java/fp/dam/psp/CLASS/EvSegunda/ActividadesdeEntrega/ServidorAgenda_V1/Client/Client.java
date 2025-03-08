package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ServidorAgenda_V1.Client;

//! --- IMPORTS ---

import java.net.*;
import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;
import javax.crypto.*;

//! --- END IMPORTS ---

public class Client extends JFrame {

    // TODO: Conexiones al servidor
    private Socket sck;
    private DataInputStream in;
    private DataOutputStream out;

    // TODO_ Claves
    private static final String KY_Path = "src\\main\\java\\fp\\dam\\psp\\CLASS\\EvSegunda\\ActividadesdeEntrega\\ServidorAgenda_1\\Key\\cliente.p12";
    private static final String KY_Pass = "cliente"; // ! Contraseña
    private SecretKey aesKey;
    private PublicKey serverPubKey;

    // TODO: Componentes Swing
    private static final long serialVersionUID = 1L;

    // ** TODO: CLIENTE
    private JTextArea txt_CT = new JTextArea();

    // ** TODO: Servidor
    private JTextArea SV_txt = new JTextArea();

    // ** TODO: Nota & Mensaje
    JTextPane nota = new JTextPane();
    JTextArea mensaje = new JTextArea();

    // ** TODO: Boton_Enviar
    private JButton enviar = new JButton("ENVIAR");

    public Client() {

        super("Servidor de contactos con cifrado V1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(700, 500);

        // TODO_ SWING CLIENTE
        // ** TODO_ TEXTO CT
        txt_CT.setEditable(true);
        txt_CT.setLineWrap(true);
        JScrollPane CT_scroll = new JScrollPane(txt_CT, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        CT_scroll.setBounds(50, 88, 250, 220);
        add(CT_scroll);

        // TODO_ SWING SERVER
        // ** TODO_ TEXTO SV
        SV_txt.setLineWrap(true);
        SV_txt.setEditable(false);
        JScrollPane SV_scroll = new JScrollPane(SV_txt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        SV_scroll.setBounds(380, 88, 250, 220);
        add(SV_scroll);

        // TODO: LABEL SV & CLIENT
        // ** TODO_ LABEL SV
        JLabel SV_Label = new JLabel("SERVIDOR");
        SV_Label.setFont(new Font("Console", Font.BOLD, 20));
        SV_Label.setBounds(430, 50, 200, 30);
        add(SV_Label);
        // ** TODO_ LABEL CT
        JLabel CT_Label = new JLabel("CLIENTE - ESCRIBE");
        CT_Label.setFont(new Font("Console", Font.BOLD, 20));
        CT_Label.setBounds(80, 50, 200, 30);
        add(CT_Label);

        // **TODO: BOTON-ENVIAR
        enviar.setBounds(280, 340, 140, 40);
        enviar.addActionListener(this::enviarMensajes); // ! Metodo: Accion de enviar
        add(enviar);

        // **TODO: Mensaje & Nota
        mensaje.setEditable(false);
        mensaje.setText("MENSAJES DE ERORR");
        mensaje.setForeground(Color.red);
        mensaje.setLineWrap(true);
        JScrollPane MSG_scroll = new JScrollPane(mensaje, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        MSG_scroll.setBounds(50, 390, 580, 60);
        add(MSG_scroll);

        nota.setEditable(false);
        nota.setText(
                "Introduce tu petición según el formato:\n- nombre: teléfono (añadir contacto), buscar:nombre (buscar contacto),eliminar:nombre (eliminar), contactos (listar todos)");
        nota.setFont(new Font("Consolas", Font.BOLD, 9));
        nota.setBackground(Color.LIGHT_GRAY);
        nota.setEditable(false);

        // TODO: CENTRAR TEXTO DE NOTA:
        StyledDocument doc = nota.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        nota.setBounds(59, 5, 580, 40);
        add(nota);

        setLocationRelativeTo(null);

        // ** TODO: CONEXIONES CON EL SERVIDOR
        Conexion();

    }
    // !---- END CONSTRUCTOR---

    // TODO: CONFIGURACION DE LO DEMAS :)

    // ! ---METODOS---

    // TODO: METODO CONECTAR SERVIDOR
    private void Conexion() {
        try {
            sck = new Socket("localhost", 6000);
            in = new DataInputStream(sck.getInputStream());
            out = new DataOutputStream(sck.getOutputStream());

            generarClaveSimetrica();
            intercambiarClaves();
        } catch (Exception e) {
            mensaje.setText("Fallo en la conexión al servidor");
        }
    }

    private void generarClaveSimetrica() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        aesKey = keyGen.generateKey();
    }

    private void intercambiarClaves() throws Exception {
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        Certificate cert = certFactory.generateCertificate(new FileInputStream("server.crt"));
        serverPubKey = cert.getPublicKey();

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, serverPubKey);
        byte[] encryptedAesKey = cipher.doFinal(aesKey.getEncoded());

        out.writeInt(encryptedAesKey.length);
        out.write(encryptedAesKey);
        out.flush();
    }

    // TODO: METODO ENVIAR
    private void enviarMensajes(ActionEvent e) {
        try {
            String mensajeTexto = txt_CT.getText().trim();
            if (mensajeTexto.isEmpty()) {
                mensaje.setText("No hay mensaje para enviar");
                return;
            }

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] mensajeCifrado = cipher.doFinal(mensajeTexto.getBytes());

            out.writeInt(mensajeCifrado.length);
            out.write(mensajeCifrado);
            out.flush();

            recibirRespuesta();
        } catch (Exception ex) {
            mensaje.setText("Error al enviar mensaje");
        }

    }

    private void recibirRespuesta() throws Exception {
        int length = in.readInt();
        byte[] mensajeCifrado = new byte[length];
        in.readFully(mensajeCifrado);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        String respuesta = new String(cipher.doFinal(mensajeCifrado));

        SV_txt.append(respuesta + "\n");
    }

    // !---- MAIN ---
    // TODO: MAIN
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Client().setVisible(true));
    }
    // !---- END MAIN ---

    // !---- MENSAJES DE ERROR ---
    // TODO: Mensjaes de error
    // private void Error(String m) {
    // mensaje.setText("> Error: " + m + "\n");
    // mensaje.setForeground(Color.red);
    // }
}
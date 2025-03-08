package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ServidorAgenda_V1.Client;

//! --- IMPORTS ---

import java.net.*;
import java.io.*;
import java.security.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;
import javax.crypto.*;

//! --- IMPORTS ---

public class Client extends JFrame {

    // TODO: Conexiones al servidor
    private Socket sck;
    private DataInputStream in;
    private DataOutputStream out;

    // TODO_ Claves
    private static final String KY_Path = "src\\main\\java\\fp\\dam\\psp\\CLASS\\EvSegunda\\ActividadesdeEntrega\\ServidorAgenda_1\\Key\\cliente.p12";
    private static final String KY_Pass = "cliente"; // ! Contraseña

    // TODO: Componentes Swing
    private static final long serialVersionUID = 1L;

    // ** TODO: CLIENTE
    private JTextArea txt_CT = new JTextArea();

    // ** TODO: Servidor
    private JTextArea SV_txt = new JTextArea();

    // ** TODO: Nota & Mensaje
    JTextPane nota1 = new JTextPane();
    JTextArea mensaje = new JTextArea();

    // ** TODO: Boton_Enviar
    private JButton enviar = new JButton("ENVIAR");

    public Client() {

        super("Servidor de contactos con cifrado V1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(690, 490);

        // TODO_ SWING CLIENTE
        // ** TODO_ TEXTO CT
        txt_CT.setEditable(true);
        txt_CT.setLineWrap(true);
        JScrollPane CT_scroll = new JScrollPane(txt_CT, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        CT_scroll.setBounds(50, 88, 220, 220);
        add(CT_scroll);

        // TODO_ SWING SERVER
        // ** TODO_ TEXTO SV
        SV_txt.setLineWrap(true);
        SV_txt.setEditable(false);
        JScrollPane SV_scroll = new JScrollPane(SV_txt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        SV_scroll.setBounds(350, 88, 220, 220);
        add(SV_scroll);

        // TODO: LABEL SV & CLIENT
        // ** TODO_ LABEL SV
        JLabel SV_Label = new JLabel("SERVIDOR");
        SV_Label.setFont(new Font("Console", Font.BOLD, 20));
        SV_Label.setBounds(350, 50, 200, 30);
        add(SV_Label);
        // ** TODO_ LABEL CT
        JLabel CT_Label = new JLabel("CLIENTE - ESCRIBE");
        CT_Label.setFont(new Font("Console", Font.BOLD, 20));
        CT_Label.setBounds(45, 50, 200, 30);
        add(CT_Label);

        // **TODO: BOTON-ENVIAR
        enviar.setBounds(349, 340, 119, 36);
        enviar.addActionListener(this::enviarMensajes); // ! Metodo: Accion de enviar
        add(enviar);

        // **TODO: Mensaje & Nota
        mensaje.setEditable(false);
        mensaje.setLineWrap(true);
        JScrollPane MSG_scroll = new JScrollPane(mensaje, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        MSG_scroll.setBounds(50, 340, 200, 90);
        add(MSG_scroll);

    }
    // !---- END CONSTRUCTOR---

    // TODO: CONFIGURACION DE LO DEMAS :)

    // ! ---METODOS---

    // TODO: METODO CONECTAR SERVIDOR

    // TODO: METODO ENVIAR
    private void enviarMensajes(ActionEvent e) {
        try {

        } catch (Exception ex) {
            mensaje.setText("Al enviar");
        }

    }

    // !---- MAIN ---
    // TODO: MAIN
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Client().setVisible(true));
    }
    // !---- END MAIN ---

    // !---- MENSAJES DE ERROR ---
    // TODO: Mensjaes de error
    private void Error(String m) {
        mensaje.setText("> Error: " + m + "\n");
        mensaje.setForeground(Color.red);
    }
}

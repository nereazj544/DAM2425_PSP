package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ServidorAgenda.Client;

//! Import 
import java.net.*;
import java.io.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Cliente extends JFrame {

    // TODO
    private Socket sck;
    private DataInputStream in;
    private DataOutputStream out;

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
    public Cliente() {
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
                "> Introduce el texto, cuando quieras finalizar escribe 'FIN' y envialo\n El recuadro azul es el servidor y el amarillo donde debes de escribri :)");
        nota1.setFont(new Font("Consolas", Font.BOLD, 15));
        nota1.setBackground(Color.LIGHT_GRAY);
        nota1.setEditable(false);

        // TODO: CENTRAR TEXTO DE NOTA:
        StyledDocument doc = nota1.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        nota1.setBounds(50, 5, 500, 40);
        add(nota1);

        // pack();
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
        } catch (Exception e) {
            mensaje("Error al conectar con el servidor");
        }

    }

    // TODO: BOTON DE ESCUCHA

    private void enviar(ActionEvent e) {
        try {

        } catch (Exception ex) {
            // TODO: handle exception
        }
    }

    // TODO Cliente Configuracion

    // TODO Mensjae de error
    private void mensaje(String msg) {
        mensaje.setText(msg + "\n");
        mensaje.setForeground(Color.RED);
    }

    // TODO Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Cliente().setVisible(true));
    }

}
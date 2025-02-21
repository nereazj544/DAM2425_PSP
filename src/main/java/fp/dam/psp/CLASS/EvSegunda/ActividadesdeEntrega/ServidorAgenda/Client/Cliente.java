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

    // TODO Swing
    private static final long serialVersionUID = 1L;

    // Cliente
    private JTextArea ClienteText = new JTextArea();

    // Servidor
    private JTextArea ServerText = new JTextArea();

    // Nota
    JTextPane nota1 = new JTextPane();
    JTextPane nota2 = new JTextPane();

    // Botones
    private JButton enviar = new JButton("ENVIAR");
    private JButton fin = new JButton("ENVIAR");

    // TODO Configuracion de la aplicacion
    public Cliente() {
        super("Agenda Signature");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        JScrollPane jp;

        // TODO Confi Server
        ServerText.setEditable(false);
        ServerText.setColumns(200);
        ServerText.setRows(200);
        ServerText.setBackground(new Color(46, 152, 193));
        // TODO: Añadir el texto del Servidor
        jp = new JScrollPane(ServerText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jp, BorderLayout.EAST);

        // TODO Confi Cliente
        ClienteText.setColumns(200);
        ClienteText.setRows(200);
        ClienteText.setBackground(new Color(226, 221, 163));
        ClienteText.setFont(new Font("Console", Font.BOLD, 10));
        // TODO: Añadir el texto del Cliente
        jp = new JScrollPane(ClienteText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jp, BorderLayout.WEST);

        // TODO: TEXTO NOTA
        nota1.setText(
                "> Introduce el texto, cuando quieras finalizar escribe 'FIN' y envialo\n El recuadro azul es el servidor y el amarillo donde debes de escribri :)");
        nota1.setFont(new Font("Consolas", Font.BOLD, 20));
        nota1.setBackground(Color.LIGHT_GRAY);
        nota1.setEditable(false);

        // TODO: CENTRAR TEXTO DE NOTA:
        StyledDocument doc = nota1.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        add(nota1, BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null);

        // TODO: Configuracion con el servidor;
        ServidorConexion();

    }

    // TODO Server Configuracion
    private void ServidorConexion() {

    }

    // TODO Cliente Configuracion

    // TODO Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Cliente().setVisible(true));
    }

}

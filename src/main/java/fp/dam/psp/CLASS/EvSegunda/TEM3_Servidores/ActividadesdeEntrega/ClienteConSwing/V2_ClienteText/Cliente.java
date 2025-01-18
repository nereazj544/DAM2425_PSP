package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.ActividadesdeEntrega.ClienteConSwing.V2_ClienteText;

/* En esta version se añade solo el recuadro del texto del cliente y servidor, que es como se indico en clase. */

//! Import 
import java.net.*;
import java.io.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Cliente extends JFrame {

    private static final long serialVersionUID = 1L;

    // TODO: SOCKET & STREAMS
    private Socket s;
    private DataInputStream in;
    private DataOutputStream out;

    // TODO: Swing Componentes

    // ? Cliente&Servidor
    private JTextArea text = new JTextArea();

    // ? Boton
    private JButton enviar = new JButton("ENVIAR");

    // TODO: OTRAS OPCIONES_SWING
    JTextPane nota = new JTextPane();

    // TODO: Configuracion de la aplicacion
    public Cliente() {
        super("Cliente Eco con Swing (V2)");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ! Panel
        JScrollPane jp;

        // TODO: TEXTO CONFIGURACION DEL TEXTO DEL SERVIDOR & CLIENTE
        text.setColumns(50);
        text.setRows(42);
        text.setBackground(new Color(226, 221, 163));
        text.setFont(new Font("Console",    Font.BOLD, 20));

        // TODO: Añadir el texto del Servidor & CLIENTE
        jp = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jp, BorderLayout.EAST);

        // TODO: BOTON DE ENVIAR
        enviar.addActionListener(this::enviar);
        enviar.setFont(new Font("console", Font.BOLD, 20));
        add(enviar, BorderLayout.SOUTH);



        // TODO: INFORMACION
        nota.setText(
                "> Introduce el texto, cuando quieras finalizar escribe 'FIN' y envialo\n Ve borrando los mensajes para poner los tuyos");
        nota.setEditable(false);
        // ? centrar texto
        StyledDocument doc = nota.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        nota.setFont(new Font("Consolas", Font.PLAIN, 20));
        nota.setBackground(Color.LIGHT_GRAY);
        add(nota, BorderLayout.NORTH);




        pack();
        setLocationRelativeTo(null);

       // TODO: Configuracion con el servidor;
       ServerOn();
    }

    // TODO: METODOS

    private void ServerOn() {
        try {
            
        } catch (Exception e) {
            text.append("> SE HA PRODUCIDO UN ERROR \nCON EL SERVIDOR POSIBLES CAUSAS:");
            text.append("\n1.- Se ha agotado el tiempo de espera del servidor.");
            text.append("\n2.- Se ha escrito 'FIN'.");

        }

    }

    private void enviar(ActionEvent e) {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Cliente().setVisible(true));
    }
}

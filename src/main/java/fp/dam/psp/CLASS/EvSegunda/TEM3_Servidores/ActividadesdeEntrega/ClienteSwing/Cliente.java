package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.ActividadesdeEntrega.ClienteSwing;

import javax.swing.*;

import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class Cliente extends JFrame {



    //! SOCKET & STREAMS
    private Socket s;
    private DataInputStream in;
    private DataOutputStream out;




    private static final long serialVersionUID = 1L;

    // ! Swing componentes

    private JTextArea textAreaCliente = new JTextArea(); // * Zona de escritura del cliente */
    private JTextArea textAreaServer = new JTextArea(); // * Zona de escritura del servidor */
    private JButton enviar = new JButton("Enviar");

    public Cliente() {
        // ? Venta
        super("Cliente Echo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ! Panel
        JScrollPane jp;

        // ! Texto_server
        textAreaServer.setEditable(false);
        textAreaServer.setColumns(30);
        textAreaServer.setRows(40);
        textAreaServer.setBackground(new Color(46, 152, 193));

        /* servidor */
        jp = new JScrollPane(textAreaServer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jp, BorderLayout.EAST);

        // ! Texto_cliente
        textAreaCliente.setColumns(70);
        textAreaCliente.setRows(40);
        textAreaCliente.setBackground(new Color(226, 221, 163));

        /* CLIENTE */
        jp = new JScrollPane(textAreaCliente, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jp, BorderLayout.WEST);

        // ! Boton
        enviar.setEnabled(true);
        enviar.addActionListener(this::enviar);
        // enviar.setBackground(new Color(32, 181, 165));
        enviar.setFont(new Font("getName()", Font.PLAIN, 20));

        // ! Informacion
        JTextPane nota = new JTextPane();
        nota.setText(
                "> Introduce el texto, cuando quieras finalizar escribe 'FIN' y envialo\n El recuadro de la derecha es el servidor y el de la izquierda donde debes de escribri :)");
        // nota.setColumns(70);
        // nota.setRows(1);
        nota.setEditable(false);

        // ? centrar texto
        StyledDocument doc = nota.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        nota.setFont(new Font("Consolas", Font.PLAIN, 20));
        nota.setBackground(Color.LIGHT_GRAY);

        // ADD
        add(enviar, BorderLayout.SOUTH);
        add(nota, BorderLayout.NORTH);

        // ! Configuracion
        pack();
        setLocationRelativeTo(null);


        conServer();
    }

    // ! Metdos


    private void conServer() {
        try {
            
            DataInputStream in = new DataInputStream(s.getInputStream());

            while (true) {
                String mensaje = in.readUTF();
                textAreaServer.append(mensaje + "\n");
            }

        } catch (Exception e) {

        }
    }

    private void enviar(ActionEvent e) {
        try {
            
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            out.writeUTF(textAreaCliente.getText());
            textAreaCliente.setText("");

        } catch (Exception ex) {
            // TODO: handle exception
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Cliente().setVisible(true));
    }

}

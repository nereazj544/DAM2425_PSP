package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.ActividadesdeEntrega.ClienteSwing;

import javax.swing.*;

import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class Cliente extends JFrame {

    // ! SOCKET & STREAMS
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

            s = new Socket("localhost", 6000);
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());

            textAreaCliente.append("Conectado al servidor");
            new Thread(() -> {

                try {
                    while (true) {
                        String mensaje;

                        while ((mensaje = in.readUTF()) != null) {
                            textAreaServer.append("> Servidor: " + mensaje + "\n");
                        }
                    }
                } catch (Exception e) {
                    textAreaServer.append("Error al conectar con el servidor\n");
                }

            }).start();
        } catch (IOException e) {
            textAreaServer.append("Error al conectar con el servidor\n");
        }
    }

    private void enviar(ActionEvent e) {
        try {

            String m = textAreaCliente.getText().trim();

            if (!m.isEmpty()) {
                out.writeUTF(m);
                textAreaCliente.setText("");

                if (m.equalsIgnoreCase("fin")) {
                    textAreaCliente.append("> La conexion se ha finalizado\n");
                    s.close();
                }
            }


            /*
             * 
             DataOutputStream out = new DataOutputStream(s.getOutputStream());
             
            out.writeUTF(textAreaCliente.getText());
            textAreaCliente.setText("");
            if (textAreaCliente.getText().equalsIgnoreCase("fin")
            || textAreaCliente.getText().equalsIgnoreCase("FIN")) {

                textAreaServer.append("> SE HA ESCRITO FIN Y SE HA FINALIZADO LA CONEXION");
                System.out.println("> SE HA ESCRITO FIN Y SE HA FINALIZADO LA CONEXION");
            }
            */

        } catch (Exception ex) {
            // TODO: handle exception
            textAreaServer.append("> Error al enviar mensaje o \n se ha finalizado la conexion con el servidor\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Cliente().setVisible(true));
    }

}

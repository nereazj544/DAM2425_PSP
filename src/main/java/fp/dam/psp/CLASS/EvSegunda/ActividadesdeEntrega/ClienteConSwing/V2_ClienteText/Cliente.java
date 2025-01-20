package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ClienteConSwing.V2_ClienteText;

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
        text.setFont(new Font("Console", Font.BOLD, 20));

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

    // TODO METODO SERVIDOR
    private void ServerOn() {
        try {
            // TODO: Socket & Stream
            s = new Socket("localhost", 6000);
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
            if (!s.isConnected() && !s.isClosed()) {
                text.append("\nSERIVDOR DESACTIVADO\n");
            } else {
                text.append("\nSERIVDOR ACTIVO\n");
            }

            new Thread(() -> {
                try {
                    while (true) {
                        String mClient;

                        while ((mClient = in.readUTF()) != null) {
                            text.append("\n> Servidor recibio mensajes.");
                            text.append("\n " + mClient + "\n");
                        }
                    }
                } catch (Exception e) {
                    text.append("\n> Se ha cerrado la conexion con el Servidor.\n");
                    enviar.setEnabled(false);
                }

            }).start();

        } catch (Exception e) {
            text.append("> SE HA PRODUCIDO UN ERROR \nCON EL SERVIDOR POSIBLES CAUSAS:");
            text.append("\n1.- Se ha agotado el tiempo de espera del servidor.");
            text.append("\n2.- Se ha escrito 'FIN'.");
            text.append("\n> Error con la \nconexion al Servidor\n");
            text.append(
                    "\n> Más informacion acerca del error: \n Se ha cerrado la conexion \no a ocurrido algun otro tipo de error");
            enviar.setEnabled(false);
        }

    }

    // TODO: Boton de Enviar

    private void enviar(ActionEvent e) {
        try {
            String mcliente = text.getText().trim();

            // Comprobacion de que si esta vacio
            if (!mcliente.isEmpty()) {
                out.writeUTF(mcliente);
                text.setText("");

                if (mcliente.toLowerCase().equalsIgnoreCase("fin")) {
                    text.append(
                            "\n> SE HA FINALIZADO LA CONEXION CON EL SERVIDOR. \nPARA VOLVER A TENER CONEXION CON ESTE REINICIE LA APLICACION (CIERRE Y VUELVA A ABRIR)");

                    s.close(); // Cerrar socket

                    enviar.setEnabled(false);
                }
            }
        } catch (Exception ex) {
            enviar.setEnabled(false);
            text.append("\n> Error en el envio del mensaje.");
            text.append("\n> Error: Se cerro la conexion con el cliente");

        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Cliente().setVisible(true));
    }
}

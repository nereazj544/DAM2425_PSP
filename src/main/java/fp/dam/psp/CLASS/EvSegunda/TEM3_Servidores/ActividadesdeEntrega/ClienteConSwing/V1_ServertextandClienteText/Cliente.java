package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.ActividadesdeEntrega.ClienteConSwing.V1_ServertextandClienteText;

/*
 * En esta version se añade un recuadro para las contextaciones del servidor.
 */

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

    // ? Cliente
    private JTextArea ClientText = new JTextArea();

    // ? Servidor
    private JTextArea ServerText = new JTextArea();

    // ? Boton
    private JButton enviar = new JButton("ENVIAR");

    // TODO: OTRAS OPCIONES_SWING
    JTextPane nota = new JTextPane();

    // TODO: Configuracion de la aplicacion
    public Cliente() {
        super("Cliente Eco con Swing (V1)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ! Panel
        JScrollPane jp;

        // TODO: TEXTO CONFIGURACION DEL TEXTO DEL SERVIDOR
        ServerText.setEditable(false);
        ServerText.setColumns(30);
        ServerText.setRows(40);
        ServerText.setBackground(new Color(46, 152, 193));

        // TODO: Añadir el texto del Servidor
        jp = new JScrollPane(ServerText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jp, BorderLayout.EAST);

        // TODO: TEXTO CONFIGURACION DEL TEXTO DEL CLIENTE

        ClientText.setColumns(70);
        ClientText.setRows(40);
        ClientText.setBackground(new Color(226, 221, 163));
        ClientText.setFont(new Font("Console", Font.BOLD, 10));

        // TODO: Añadir el texto del Cliente
        jp = new JScrollPane(ClientText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jp, BorderLayout.WEST);

        // TODO: BOTON DE ENVIAR
        enviar.addActionListener(this::enviar);
        enviar.setFont(new Font("console", Font.BOLD, 20));

        // TODO: TEXTO NOTA

        nota.setText(
                "> Introduce el texto, cuando quieras finalizar escribe 'FIN' y envialo\n El recuadro azul es el servidor y el amarillo donde debes de escribri :)");

        nota.setFont(new Font("Consolas", Font.BOLD, 20));
        nota.setBackground(Color.LIGHT_GRAY);
        nota.setEditable(false);

        // TODO: CENTRAR TEXTO DE NOTA:
        StyledDocument doc = nota.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        add(enviar, BorderLayout.SOUTH);
        add(nota, BorderLayout.NORTH);
        // TODO
        pack();
        setLocationRelativeTo(null);

        // TODO: Configuracion con el servidor;
        ServerConexion();

    }

    // TODO: METODOS

    // TODO: CONEXION CON EL SERVIDOR
    private void ServerConexion() {
        try {
            // ! Pueto y Host & Stream

            s = new Socket("localhost", 6000);
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
            if (!s.isConnected() && !s.isClosed()) {

                ServerText.append("\nSERIVDOR DESACTIVADO\n");
            } else {
                ServerText.append("\nSERIVDOR ACTIVO\n");

            }

            // HiloCliente(in);

            new Thread(() -> {
                try {
                    while (true) {
                        String mClient;

                        while ((mClient = in.readUTF()) != null) {
                            ServerText.append("\n> Servidor recibio mensajes.");
                            ServerText.append("\n " + mClient + "\n");

                        }

                    }
                } catch (Exception e) {
                    ServerText.append("\n> Se ha cerrado la conexion con el Servidor.\n");
                    // ServerText.append("\n> Más informacion acerca del error: \n" +
                    // e.getMessage());
                    enviar.setEnabled(false);

                }
            }).start();

        } catch (Exception e) {

            ClientText.append("> Conexión cerrada con el servidor.\n");
            ClientText.append("> Causas: se agoto el tiempo o porque se ha escrito 'FIN'\n");

            ServerText.append("\n> Error con la \nconexion al Servidor\n");
            ServerText.append(
                    "\n> Más informacion acerca del error: \n Se ha cerrado la conexion \no a ocurrido algun otro tipo de error");
            enviar.setEnabled(false);

        }

    }

    // TODO: HILO DEL CLIENTE
    // private void HiloCliente(DataInputStream in2) {

    // }

    // TODO: BOTON DE ESCUCHA

    private void enviar(ActionEvent e) {

        try {
            String mcliente = ClientText.getText().trim();

            // Comprobacion de que si esta vacio
            if (!mcliente.isEmpty()) {
                out.writeUTF(mcliente);
                ClientText.setText("");

                if (mcliente.toLowerCase().equalsIgnoreCase("fin")) {
                    ClientText.append(
                            "\n> SE HA FINALIZADO LA CONEXION CON EL SERVIDOR. \nPARA VOLVER A TENER CONEXION CON ESTE REINICIE LA APLICACION (CIERRE Y VUELVA A ABRIR)");

                    s.close(); // Cerrar socket

                    enviar.setEnabled(false);
                }
            }
        } catch (Exception ex) {
            enviar.setEnabled(false);
            ServerText.append("\n> Error en el envio del mensaje.");
            ServerText.append("\n> Error: Se cerro la conexion con el cliente");

        }

    }

    // TODO: MAIN
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Cliente().setVisible(true));
    }

}
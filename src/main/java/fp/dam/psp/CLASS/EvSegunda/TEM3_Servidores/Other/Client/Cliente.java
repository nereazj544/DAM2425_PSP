package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Other.Client;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Cliente extends JFrame {

    private static final long serialVersionUID = 1L;

    // ! SOCKET & STREAMS
    private Socket s;
    private DataInputStream in;
    private DataOutputStream out;

    // ? Componentes
    JTextArea tArea = new JTextArea();
    JButton enviar = new JButton("Enviar");

    public Cliente() {
        // ! Configuraciones de la ventana
        super("Cliente Echo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ! Panel
        JScrollPane jp;

        // ! texto
        tArea.setColumns(70);
        tArea.setRows(40);
        tArea.setBackground(new Color(226, 221, 163));
        jp = new JScrollPane(tArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jp, BorderLayout.CENTER);

        // ! Boton
        enviar.setEnabled(true);
        enviar.addActionListener(this::enviar);
        enviar.setFont(new Font("getName()", Font.PLAIN, 20));
        add(enviar, BorderLayout.SOUTH);

        // ! Info
        JTextPane nota = new JTextPane();
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
        ServerOn();

    }

    // ! METODOS

    private void ServerOn() {
        try {
            s = new Socket("localhost", 6000);
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
            tArea.append("> Conectado al servidor.\n > Estado: Ok");

            new Thread(()->{
                try {
                    while(true){
                        String m = in.readUTF();
                        tArea.append("> Servidor: " + m + "\n");
                    }
                } catch (IOException e) {
                    tArea.append("> Conexión cerrada con el servidor.\n");
                    tArea.append("> Causas: se agoto el tiempo o porque se ha escrito 'FIN'\n");
                }
            }).start();


        } catch (Exception e) {
            // TODO: handle exception
            tArea.append("> Error al conectarse con el servidor.\n > " + e.getMessage());
        }
    }
    
    private void enviar(ActionEvent e) {
        try {
            String m = tArea.getText().trim();

            if (!m.isEmpty()) {
                out.writeUTF(m);
                tArea.setText("");
                if (m.equalsIgnoreCase("fin")) {
                    tArea.append("> La conexion se ha finalizado\n");
                    s.close();
                    enviar.setEnabled(false);
                }
            }

        } catch (Exception ex) {
            tArea.append("> Error al enviar mensaje.\n > " + ex.getMessage());
            
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Cliente().setVisible(true));
    }
}

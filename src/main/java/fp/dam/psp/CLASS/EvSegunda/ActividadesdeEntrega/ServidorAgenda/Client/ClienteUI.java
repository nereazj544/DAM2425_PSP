package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ServidorAgenda.Client;

import javax.swing.*;
import java.awt.*;

public class ClienteUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Agenda - Cliente");
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            
            // Caja de texto Cliente
            JTextArea clienteTextArea = new JTextArea();
            clienteTextArea.setBounds(50, 50, 200, 200);
            frame.add(clienteTextArea);
            
            // Caja de texto Servidor (no editable)
            JTextArea servidorTextArea = new JTextArea();
            servidorTextArea.setBounds(350, 50, 200, 200);
            servidorTextArea.setEditable(false);
            frame.add(servidorTextArea);
            
            // Mensajes de error/conexión
            JLabel mensajeError = new JLabel();
            mensajeError.setBounds(50, 260, 123, 20);
            frame.add(mensajeError);
            
            // Botón de Enviar con bordes redondeados
            JButton enviarButton = new JButton("ENVIAR") {
                @Override
                protected void paintComponent(Graphics g) {
                    if (getModel().isArmed()) {
                        g.setColor(Color.DARK_GRAY);
                    } else {
                        g.setColor(Color.GREEN);
                    }
                    g.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                    super.paintComponent(g);
                }
            };
            enviarButton.setBounds(240, 330, 119, 36);
            enviarButton.setFocusPainted(false);
            enviarButton.setBorderPainted(false);
            enviarButton.setContentAreaFilled(false);
            frame.add(enviarButton);
            
            frame.setVisible(true);
        });
    }
}

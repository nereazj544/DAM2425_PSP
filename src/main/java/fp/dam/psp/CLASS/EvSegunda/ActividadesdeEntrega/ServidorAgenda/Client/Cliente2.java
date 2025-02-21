package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ServidorAgenda.Client;

import java.awt.*;
import javax.swing.*;

public class Cliente2 extends JFrame {

    private static final long serialVersionUID = 1L;

    // Componentes de la interfaz
    private JTextArea ClienteText = new JTextArea(10, 20);
    private JTextArea ServerText = new JTextArea(10, 20);
    private JTextField mensajeEstado = new JTextField(30);
    private JButton enviar = new JButton("ENVIAR");
    private JButton fin = new JButton("FINALIZAR");

    public Cliente2() {
        super("Agenda - Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Iconos superiores
        JLabel icono1 = new JLabel("🖥️");
        JLabel icono2 = new JLabel("🖥️");

        // Etiquetas
        JLabel etiquetaCliente = new JLabel("CLIENTE");
        JLabel etiquetaServidor = new JLabel("SERVIDOR");

        // Configurar áreas de texto
        ClienteText.setLineWrap(true);
        ClienteText.setWrapStyleWord(true);
        ServerText.setLineWrap(true);
        ServerText.setWrapStyleWord(true);
        ServerText.setEditable(false);

        JScrollPane scrollCliente = new JScrollPane(ClienteText);
        JScrollPane scrollServidor = new JScrollPane(ServerText);

        // Mensaje de error/conexión
        mensajeEstado.setEditable(false);

        // Personalizar botones
        enviar.setBackground(Color.GREEN);
        enviar.setForeground(Color.BLACK);
        fin.setBackground(Color.RED);
        fin.setForeground(Color.WHITE);

        // Añadir componentes al GridBagLayout
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Primera fila - Título con iconos
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(icono1, gbc);
        gbc.gridx = 1;
        add(new JLabel("Agenda - Cliente", SwingConstants.CENTER), gbc);
        gbc.gridx = 2;
        add(icono2, gbc);

        // Segunda fila - Etiquetas Cliente/Servidor
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(etiquetaCliente, gbc);
        gbc.gridx = 2;
        add(etiquetaServidor, gbc);

        // Tercera fila - Áreas de texto
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(scrollCliente, gbc);
        gbc.gridx = 2;
        add(scrollServidor, gbc);

        // Cuarta fila - Mensaje de estado
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(mensajeEstado, gbc);

        // Quinta fila - Botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(enviar);
        panelBotones.add(fin);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(panelBotones, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Cliente2::new);
    }
}


package fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad16;

import javax.swing.*;
import java.awt.*;

public class Chinos extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int diameter = Math.min(width, height) / 5; // Diámetro de los círculos

        // Círculo 孔夫子
        g.setColor(Color.CYAN);
        g.fillOval(width / 2 - diameter / 2, height / 4 - diameter / 2, diameter, diameter);

        // MESA
        g.setColor(Color.RED);
        g.fillOval(width / 2 - diameter / 2, height / 2 - diameter / 2, diameter, diameter);

        // Círculo inferior (verde)
        g.setColor(Color.GREEN);
        g.fillOval(width / 2 - diameter / 2, 3 * height / 4 - diameter / 2, diameter, diameter);

        // Círculo negro
        g.setColor(Color.BLACK);
        g.fillOval(3 * width / 4 - diameter / 2, height / 2 - diameter / 2, diameter, diameter);

        // Círculo amarillo (separado)
        g.setColor(Color.YELLOW);
        g.fillOval(width / 8 - diameter / 2, height / 2 - diameter / 2, diameter, diameter);

        // Texto para el círculo amarillo
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.setColor(Color.BLACK);
        g.drawString("Zhuan Zi", width / 8 - 25, height / 2 + diameter / 2 + 20);
        g.drawString("Xun Zi", 3 * width / 4 - 10, height / 2 + diameter / 2 + 20);
        g.drawString("Yang Zhu", width / 2 - 2, 3 * height / 4 + diameter / 2 + 20);
        g.drawString("MESA", width / 2 - 2, height / 2 + diameter / 2 + 20);
        g.drawString("Kong Fuzi", width / 2 - 2, height / 4 + diameter / 2 + 20);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Círculos Alineados");
        frame.add(new Chinos());
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        // Panel con los botones en la parte superior
        JPanel buttonPanel = new JPanel();
        JButton pausa = new JButton("PAUSA");
        JButton reanudar = new JButton("REANUDAR");
        buttonPanel.add(pausa);
        buttonPanel.add(reanudar);
        contentPane.add(buttonPanel, BorderLayout.NORTH);

        // Panel con el dibujo de los círculos
        Chinos panelCirculos = new Chinos();
        contentPane.add(panelCirculos, BorderLayout.CENTER);

        // Cuadro de texto en la parte inferior
        // JTextField textField = new JTextField();
        // textField.setPreferredSize(new Dimension(600, 30));
        // contentPane.add(textField, BorderLayout.SOUTH);
        JTextArea textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(600, 30));
        contentPane.add(textArea, BorderLayout.SOUTH);
        textArea.setEditable(false);

        // Mostrar el frame
        frame.setVisible(true);
    }
}

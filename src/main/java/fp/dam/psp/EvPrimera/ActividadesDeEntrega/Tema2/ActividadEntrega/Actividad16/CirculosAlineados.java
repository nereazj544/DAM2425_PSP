package fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad16;

import javax.swing.*;
import java.awt.*;

public class CirculosAlineados extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int diameter = Math.min(width, height) / 5; // Diámetro de los círculos

        // Círculo superior (rojo)
        g.setColor(Color.RED);
        g.fillOval(width/2 - diameter/2, height/4 - diameter/2, diameter, diameter);

        // Círculo central (azul)
        g.setColor(Color.BLUE);
        g.fillOval(width/2 - diameter/2, height/2 - diameter/2, diameter, diameter);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("MESA", width/2 - 2, height/2 + diameter/2 + 20);

        // Círculo inferior (verde)
        g.setColor(Color.GREEN);
        g.fillOval(width/2 - diameter/2, 3*height/4 - diameter/2, diameter, diameter);

        // Círculo negro
        g.setColor(Color.BLACK);
        g.fillOval(3*width/4 - diameter/2, height/2 - diameter/2, diameter, diameter);

        // Círculo amarillo (separado)
        g.setColor(Color.YELLOW);
        g.fillOval(width/8 - diameter/2, height/2 - diameter/2, diameter, diameter);

        // Agregar texto al círculo amarillo
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("Amarillo", width/8 - 25, height/2 + diameter/2 + 20);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Círculos Alineados");
        frame.add(new CirculosAlineados());
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
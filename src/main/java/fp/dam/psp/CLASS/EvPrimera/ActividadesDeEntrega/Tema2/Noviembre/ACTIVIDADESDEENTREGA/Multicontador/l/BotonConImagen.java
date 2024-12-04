package fp.dam.psp.CLASS.EvPrimera.ActividadesDeEntrega.Tema2.Noviembre.ACTIVIDADESDEENTREGA.Multicontador.l;
import javax.swing.*;
import java.awt.*;

public class BotonConImagen extends JFrame {
    public BotonConImagen() {
        setTitle("Botón con Imagen");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un botón
        JButton boton = new JButton();

        // Cargar la imagen
        ImageIcon icono = new ImageIcon("src\\main\\java\\fp\\dam\\psp\\EvPrimera\\TEMA2\\Noviembre\\ACTIVIDADESDEENTREGA\\Multicontador\\l\\fondo.jpg");

        // Redimensionar la imagen si es necesario
        Image img = icono.getImage();
        Image imgRedimensionada = img.getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon iconoRedimensionado = new ImageIcon(imgRedimensionada);

        // Establecer el icono en el botón
        boton.setIcon(iconoRedimensionado);

        // Añadir el botón al frame
        add(boton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BotonConImagen());
    }
}
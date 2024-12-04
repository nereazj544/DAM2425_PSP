package fp.dam.psp.CLASS.EvPrimera.ActividadesDeEntrega.Tema2.Noviembre.ACTIVIDADESDEENTREGA.Multicontador.l;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiContador extends JFrame {
    private JTextField[] parciales; // Contadores parciales
    private JTextField global;     // Contador global

    public MultiContador() {
        setTitle("Multi-Contador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1)); // 4 filas, 1 columna

        parciales = new JTextField[3];
        global = new JTextField("0");
        global.setEditable(false);

        // Crear las 3 filas de contadores parciales
        for (int i = 0; i < 3; i++) {
            JPanel fila = new JPanel(new FlowLayout());
            JButton reset = new JButton("RESET");
            JTextField contador = new JTextField("0", 5);
            contador.setEditable(false);
            JButton incrementar = new JButton("+");

            // Añadir funcionalidad al botón "+"
            incrementar.addActionListener(e -> {
                int valor = Integer.parseInt(contador.getText());
                contador.setText(String.valueOf(++valor));
                actualizarGlobal();
            });

            // Añadir funcionalidad al botón "RESET"
            reset.addActionListener(e -> {
                contador.setText("0");
                actualizarGlobal();
            });

            parciales[i] = contador;
            fila.add(reset);
            fila.add(contador);
            fila.add(incrementar);
            add(fila);
        }

        // Crear la fila del contador global
        JPanel filaGlobal = new JPanel(new FlowLayout());
        JButton resetGlobal = new JButton("RESET");
        resetGlobal.addActionListener(e -> {
            for (JTextField parcial : parciales) {
                parcial.setText("0");
            }
            global.setText("0");
        });
        filaGlobal.add(resetGlobal);
        filaGlobal.add(global);
        add(filaGlobal);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para actualizar el valor del contador global
    private void actualizarGlobal() {
        int suma = 0;
        for (JTextField parcial : parciales) {
            suma += Integer.parseInt(parcial.getText());
        }
        global.setText(String.valueOf(suma));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MultiContador::new);
    }
}

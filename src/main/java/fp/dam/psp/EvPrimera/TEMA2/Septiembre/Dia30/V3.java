package fp.dam.psp.EvPrimera.TEMA2.Septiembre.Dia30;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class V3 extends JFrame {
    private static final long serialVersionUID = 1L;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private JLabel hora;

    public V3() {
        super("Reloj");
        hora = new JLabel(formatter.format(LocalDateTime.now()));
        hora.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(70, 70, 70, 70),
                hora.getBorder()
        ));
        hora.setFont(hora.getFont().deriveFont(30f));
        hora.setHorizontalAlignment(JLabel.CENTER);
        getContentPane().add(hora);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
        public void run() {
        Runnable actualizarHora = new Runnable() {
            public void run() {
                hora.setText(formatter.format(LocalDateTime.now()));
            }
        };
        while (true) {
            SwingUtilities.invokeLater(actualizarHora);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
    private void iniciar() {
        setVisible(true);
//        new Thread(this::run, "segundero").start(); //Metodo abstrazto (? tambien con una expresion lamda
        new Thread(() -> { //aqui el metodo run
            Runnable actualizarHora = new Runnable() {
                public void run() {
                    hora.setText(formatter.format(LocalDateTime.now()));
                }
            };
            while (true) {
                SwingUtilities.invokeLater(actualizarHora);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            }
        });

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Reloj().iniciar();
            }
        });
    }
}
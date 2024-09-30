package fp.dam.psp.EvPrimera.TEMA2.Dia30;


import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RelojV2 extends JFrame {
        private static final long serialVersionUID = 1L;
        private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        private JLabel hora;

        public RelojV2() {
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

        private void iniciar() {
            setVisible(true);
            new Thread(new RelojRunnable(this), "segundero").start();
        }

        public void actualizarHora() {
            hora.setText(formatter.format(LocalDateTime.now()));
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new RelojV2().iniciar());
        }
    }

    class RelojRunnable implements Runnable {
        private RelojV2 relojV2;

        public RelojRunnable(RelojV2 reloj) {
            this.relojV2 = reloj;
        }

        @Override
        public void run() {
            while (true) {
                SwingUtilities.invokeLater(() -> relojV2.actualizarHora());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }
    }

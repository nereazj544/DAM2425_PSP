package fp.dam.psp.EvPrimera.TEMA2.Septiembre.Dia23;


// TODO: Lo que hay que hacer en esta version, creando el implements runnable

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

        public static void main(String[] args) {
            //Swing lo usa para la cola de eventos y se ejecuta cuando deba
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
//                    new RelojV2().iniciar();
                    //metodo de instancia, hace referencia al objeto reloj, que es de tipo runnable. No siempre que se cree el objeto
                }
            });
        }
}

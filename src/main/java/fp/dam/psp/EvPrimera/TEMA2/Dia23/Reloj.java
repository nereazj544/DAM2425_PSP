package fp.dam.psp.EvPrimera.TEMA2.Dia23;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reloj extends JFrame implements Runnable {
    private static final long serialVersionUID = 1L;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private JLabel hora;

    public Reloj() {
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
        new Thread(this, "segundero").start();
        //Se inicia el hilo del reloj, y se le pasa la instancia del run
        // ? La unica manera para crear el hilo es con el Thread y no con el Runnable
    }
    public static void main(String[] args) {
                //Swing lo usa para la cola de eventos y se ejecuta cuando deba
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Reloj().iniciar(); //metodo de instancia, hace referencia al objeto reloj, que es de tipo runnable. No siempre que se cree el objeto
            }
        });
    }
}

package fp.dam.psp.EvPrimera.TEMA2.Septiembre.Dia30;


import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Los objetos Runnable no son hilos.
class RelojClaseTarea implements Runnable {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private JLabel hora;

    public RelojClaseTarea(JLabel hora){
        this.hora = hora; //
    }

    @Override
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
}

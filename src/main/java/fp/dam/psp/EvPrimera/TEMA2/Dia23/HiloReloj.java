package fp.dam.psp.EvPrimera.TEMA2.Dia23;

import javax.swing.*;
import java.time.LocalDateTime;

import static com.sun.org.apache.xerces.internal.util.DOMUtil.setVisible;

public class HiloReloj implements  Runnable{
    public void run() {
        Runnable actualizarHora = new Runnable() {
            public void run() {

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
}

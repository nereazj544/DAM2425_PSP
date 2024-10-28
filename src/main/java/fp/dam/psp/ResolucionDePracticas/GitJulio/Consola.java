package fp.dam.psp.ResolucionDePracticas.GitJulio;

import javax.swing.*;

public class Consola extends JTextArea {

    public Consola(int rows, int columns) {
        super(rows, columns);
    }

    public void println(String s) {
        SwingUtilities.invokeLater(() -> { append(s); append("\n"); });
    }

    public void print(String s) {
        SwingUtilities.invokeLater(() -> { append(s); });
    }
}

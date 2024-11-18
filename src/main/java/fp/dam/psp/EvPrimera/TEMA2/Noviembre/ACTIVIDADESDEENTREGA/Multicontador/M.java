package fp.dam.psp.EvPrimera.TEMA2.Noviembre.ACTIVIDADESDEENTREGA.Multicontador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class M extends JFrame implements WindowListener {
    private static final long serialVersionUID = 1L;
    static JTextArea text = new JTextArea("0");
    JButton SUMAR = new JButton("+");
    JButton REST = new JButton("RESET");
    private int contador = 0;

    public M() {
        super("Multicontador");
        text.setEditable(false);

        // !PANEL
        Container CPanel = getContentPane();
        CPanel.setPreferredSize(new Dimension(400, 100));

        // ! Layout
        setLayout(new BorderLayout());

        // !Componentes
        add(SUMAR, BorderLayout.EAST);
        add(text, BorderLayout.CENTER);
        add(REST, BorderLayout.WEST);

        text.setEditable(false);


        //!METODOS
        Suma();
        Resetear();

        pack();
        this.addWindowListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    private void Suma() {

        SUMAR.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                contador++;
                text.setText(Integer.toString(contador));
            }
        });
    }
    private void Resetear() {

        REST.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                contador= 0;
                text.setText("0");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new M().setVisible(true);
            }
        });
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        // TODO finalizar hilos de forma ordenada antes de salir
        System.exit(0);
        // TODO: Se pone el metodo de fin

        // Aqui seria:
        /*
         * f1.interrupt();
         *
         * try {
         * f1.join();
         * } catch (Exception ek) {
         * }
         *
         */

    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}

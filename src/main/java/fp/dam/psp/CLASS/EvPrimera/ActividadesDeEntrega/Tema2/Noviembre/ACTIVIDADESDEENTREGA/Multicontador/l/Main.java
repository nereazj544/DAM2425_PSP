package fp.dam.psp.CLASS.EvPrimera.ActividadesDeEntrega.Tema2.Noviembre.ACTIVIDADESDEENTREGA.Multicontador.l;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Main extends JFrame implements WindowListener  {

    private static final long serialVersionUID = 1L;
    static JTextArea text = new JTextArea();
    JButton SUMAR = new JButton("+");
    JButton REST = new JButton("RESET");

    public Main(){
        super("Multicontador");
        text.setEditable(false);
        Container contentPanel = getContentPane();
        contentPanel.setPreferredSize(new Dimension(900, 700)); //? Dimensiones
        JPanel panel = new JPanel();
        this.addWindowListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


        // * ==== BOTONES ====

        pack();
        setLocationRelativeTo(null);
    }



    private void iniciar() {
        setVisible(true);
        //TODO: Se pone "[hilo].star()" pa que carrule, y si no carrula revisar el metodo de reanudar (sino es notify es notifyall)

    }

    private static void crear() {
        // new fp.dam.psp.EvPrimera.TEMA2.Noviembre.ACTIVIDADESDEENTREGA.Multicontador.Main.inciar();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::crear);
    }


    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        // TODO finalizar hilos de forma ordenada antes de salir
        System.exit(0);
        //TODO: Se pone el metodo de fin


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


package fp.dam.psp.ResolucionDePracticas.GitJulio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class Main extends JFrame implements WindowListener {

    BotonPausa pausa;
    Consola consola;

    Main() {
        super ("GLOBOS");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(this);
        ((JPanel) getContentPane()).setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10,10),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.gray, 2),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                )));
        getContentPane().add(new ToolBar(), BorderLayout.NORTH);
        JScrollPane sp = new JScrollPane(consola = new Consola(40, 80), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(sp , BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main()::iniciar);
    }

    private void iniciar() {
        setVisible(true);
    }

    private void finalizar(ActionEvent e) {
        dispose();
        System.exit(0);
    }

    private void pausar() {

    }

    private void reanudar() {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        finalizar(null);
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

    private class ToolBar extends JToolBar {
        ToolBar() {
            setFloatable(false);
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add(new BotonPausa(
                    new ImageIcon(Main.class.getResource("/pause.png")),
                    new ImageIcon(Main.class.getResource("/play.png"))));
            JButton exit = new JButton(new ImageIcon(Main.class.getResource("/exit.png")));
            exit.addActionListener(Main.this::finalizar);
            add(exit);
        }
    }

    private class BotonPausa extends JToggleButton {
        ImageIcon iconoPausa;
        ImageIcon iconoReanudar;

        BotonPausa(ImageIcon iconoPausa, ImageIcon iconoReanudar) {
            setIcon(this.iconoPausa = iconoPausa);
            this.iconoReanudar = iconoReanudar;
            addActionListener(this::pausarReanudar);
        }

        public void pausarReanudar(ActionEvent e) {
            if (isSelected()) {
                SwingUtilities.invokeLater(() -> setIcon(iconoReanudar));
                Main.this.pausar();
            }
            else {
                SwingUtilities.invokeLater(() -> setIcon(iconoPausa));
                Main.this.reanudar();
            }
        }
    }

}
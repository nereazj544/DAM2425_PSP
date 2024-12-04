package fp.dam.psp.Other.Productores;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Main extends JFrame implements WindowListener {
	private static final long serialVersionUID = 1L;
	private static final JTextArea textArea = new JTextArea();
	private JButton pausa = new JButton("PAUSA");
	private JButton reanudar = new JButton("REANUDAR");

	private Almacen almacen = new Almacen(10);
	private Prodructor p = new Prodructor(1000, almacen);
	private Consumidor c = new Consumidor(almacen, 1000);

	public Main() {
		super("PRODUCTORES");
		this.addWindowListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setPreferredSize(new Dimension(900, 700));
		JPanel panel = new JPanel();
		pausa.addActionListener(this::pausa);
		panel.add(pausa, BorderLayout.WEST);
		reanudar.setEnabled(false);
		reanudar.addActionListener(this::reanudar);
		panel.add(reanudar, BorderLayout.EAST);
		contentPane.add(panel, BorderLayout.NORTH);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
	}

	public static void actualizar(String msg) {
		SwingUtilities.invokeLater(() -> textArea.append(msg));
	}

	private void pausa(ActionEvent e) {
		pausa.setEnabled(false);
		reanudar.setEnabled(true);
		textArea.append("PAUSADO\n");
		// *SE PAUSAN HILOS
		p.suspender();
		c.suspender();

	}

	private void reanudar(ActionEvent e) {
		pausa.setEnabled(true);
		reanudar.setEnabled(false);
		textArea.append("REANUDADO\n");
		p.reanudar();
		c.reanudar();

	}

	private void iniciar() {
		setVisible(true);
		//TODO: AQUI SE INICIAN LOS HILOS
		p.start();
		c.start();

	}

	private static void crear() {
		new Main().iniciar();
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
		p.fin();
		c.fin();
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

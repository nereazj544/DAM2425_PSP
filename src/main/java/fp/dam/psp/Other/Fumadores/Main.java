package fp.dam.psp.Other.Fumadores;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Main extends JFrame implements WindowListener {
	private static final long serialVersionUID = 1L;
	private static final JTextArea textArea = new JTextArea();
	private JButton pausa = new JButton("PAUSA");
	private JButton reanudar = new JButton("REANUDAR");

	private Mesa m = new Mesa();
	private Agente agente = new Agente(m);
	private Fumador f1 = new Fumador(getName(), Ingredientes.TABACO, m);
	private Fumador f2 = new Fumador(getName(), Ingredientes.PAPEL, m);
	private Fumador f3 = new Fumador(getName(), Ingredientes.CERILLAS, m);

	public Main() {
		super("FUMADORES");
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
		f1.suspender();
		f2.suspender();
		f3.suspender();
		agente.suspender();

	}

	private void reanudar(ActionEvent e) {
		pausa.setEnabled(true);
		reanudar.setEnabled(false);
		textArea.append("REANUDADO\n");

		f1.reanudar();
		f2.reanudar();
		f3.reanudar();
		agente.reanudar();
	}

	private void iniciar() {
		setVisible(true);
		f1.start();
		f2.start();
		f3.start();
		agente.start();

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

		f1.fin();
		f2.fin();
		f3.fin();
		agente.fin();

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

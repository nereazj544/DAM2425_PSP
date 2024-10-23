package fp.dam.psp.EvPrimera.Other.Chinos;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Main extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	private static final JTextArea textArea = new JTextArea();
	private JButton pausa = new JButton("PAUSA");
	private JButton reanudar = new JButton("REANUDAR");

	// TODO Se invocan las clases (las de los hilos)
	Palillos[] p = new Palillos[5];
	Filosofos[] f = new Filosofos[5];

	public Main() {
		super("Filosofos chinos");
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

		// ! Invocamos un metodo para poder iniciar los filosofos
		iniciarF();
	}

	private void iniciarF() {
		for (int i = 0; i < 5; i++) {
			p[i] = new Palillos();
		}
		String[] n = { "孔夫子 ", "楊朱 ", "荀子", "商鞅", "莊子" };
		for (int i = 0; i < 5; i++) {
			f[i] = new Filosofos(n[i], p[i], p[(i + 1) % 5]);
		}
	}

	public static void actualizar(String msg) {
		SwingUtilities.invokeLater(() -> textArea.append(msg));
	}

	private void pausa(ActionEvent e) {
		pausa.setEnabled(false);
		reanudar.setEnabled(true);
		textArea.append("PAUSADO\n");
		// TODO: Se pone el metodo de parada de cada hilo
		for (Filosofos filosofos : f) {
			filosofos.suspender();

		}

	}

	private void reanudar(ActionEvent e) {
		pausa.setEnabled(true);
		reanudar.setEnabled(false);
		textArea.append("REANUDADO\n");
		// TODO: Se pone el metodo de volver a carrular de cada hilo
		for (Filosofos filosofos : f) {
			filosofos.reanudar();

		}
	}

	private void iniciar() {
		setVisible(true);
		// TODO: Se pone "[hilo].star()" pa que carrule, y si no carrula revisar el
		// metodo de reanudar (sino es notify es notifyall)
		for (Filosofos filosofos : f) {
			filosofos.start();
		}

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
		// TODO: Se pone el metodo de fin
		for (Filosofos filosofos : f) {
			filosofos.fin();
		}
		for (Filosofos filosofos : f) {
			try {
				filosofos.join();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
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

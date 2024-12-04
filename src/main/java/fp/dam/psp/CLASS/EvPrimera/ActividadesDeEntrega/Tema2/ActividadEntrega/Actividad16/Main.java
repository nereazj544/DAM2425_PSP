package fp.dam.psp.CLASS.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad16;

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

		Chinos chinos = new Chinos();



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
		
		// chinos.add(panel, BorderLayout.NORTH);
		// contentPane.add(chinos, scrollPane);
		
		
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
	/*
	 * No carrurla
	 * @Override
	 * protected void paintComponent(Graphics g) {
	 * super.paintComponent(g);
	 * int width = getWidth();
	 * int height = getHeight();
	 * int diameter = Math.min(width, height) / 5; // Diámetro de los círculos
	 * 
	 * // Círculo 孔夫子
	 * g.setColor(Color.CYAN);
	 * g.fillOval(width / 2 - diameter / 2, height / 4 - diameter / 2, diameter,
	 * diameter);
	 * 
	 * // MESA
	 * g.setColor(Color.RED);
	 * g.fillOval(width / 2 - diameter / 2, height / 2 - diameter / 2, diameter,
	 * diameter);
	 * 
	 * // Círculo inferior (verde)
	 * g.setColor(Color.GREEN);
	 * g.fillOval(width / 2 - diameter / 2, 3 * height / 4 - diameter / 2, diameter,
	 * diameter);
	 * 
	 * // Círculo negro
	 * g.setColor(Color.BLACK);
	 * g.fillOval(3 * width / 4 - diameter / 2, height / 2 - diameter / 2, diameter,
	 * diameter);
	 * 
	 * // Círculo amarillo (separado)
	 * g.setColor(Color.YELLOW);
	 * g.fillOval(width / 8 - diameter / 2, height / 2 - diameter / 2, diameter,
	 * diameter);
	 * 
	 * // Texto para el círculo amarillo
	 * g.setFont(new Font("Arial", Font.BOLD, 12));
	 * g.setColor(Color.BLACK);
	 * g.drawString("Zhuan Zi", width / 8 - 25, height / 2 + diameter / 2 + 20);
	 * g.drawString("Xun Zi", 3 * width / 4 - 10, height / 2 + diameter / 2 + 20);
	 * g.drawString("Yang Zhu", width / 2 - 2, 3 * height / 4 + diameter / 2 + 20);
	 * g.drawString("MESA", width / 2 - 2, height / 2 + diameter / 2 + 20);
	 * g.drawString("Kong Fuzi", width / 2 - 2, height / 4 + diameter / 2 + 20);
	 * }
	 */

}

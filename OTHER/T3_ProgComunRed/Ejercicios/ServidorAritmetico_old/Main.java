package T3_ProgComunRed.Ejercicios.ServidorAritmetico_old;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Main extends JFrame { //escuchar J30/11/2023 50:00 aprox

	private static final long serialVersionUID = 1L;

	static int cpWidth = 4;
	static int cpHeight = 4;
	static Dimension dims = new Dimension(45, 45);
	JTextField texto;
	public static StringBuilder textoStr = new StringBuilder();
	static Map<String, JButton> mapa = new HashMap<>();
	static StringBuilder sb = new StringBuilder();

	public Main() {
		super("ew");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(180, 45));
		constraints.gridx = 0;
		constraints.gridy = 0;

		texto = new JTextField("");
		texto.setPreferredSize(new Dimension(180, 45));
		texto.setBackground(Color.BLACK);
		texto.setForeground(Color.WHITE);
		texto.setHorizontalAlignment(JTextField.CENTER);
		texto.setEditable(false);

		panel.add(texto);
		add(panel, constraints);

		panel = new JPanel();

		String[][] botones = { { "7", "8", "9", "/" }, { "4", "5", "6", "*" }, { "1", "2", "3", "-" },
				{ "=", "0", "R", "+" } }; 

		for (int y = 0; y < cpHeight; y++) {
			for (int x = 0; x < cpWidth; x++) {
				JButton b = new JButton();
				b.setPreferredSize(dims);
				b.setName(botones[y][x]);
				b.setText(botones[y][x]);
				b.addMouseListener(ratonListener(b));
				mapa.put(b.getName(), b);
				constraints.gridx = x;
				constraints.gridy = y;
				panel.add(b, constraints);
			}
		}

		panel.setPreferredSize(new Dimension(200, 200));
		constraints.gridx = 0;
		constraints.gridy = 1;
		add(panel, constraints);

		pack();
		setLocationRelativeTo(null);
	}

	public static void main(final String[] args) throws IOException {
		SwingUtilities.invokeLater(() -> {
			new Main().setVisible(true);
		});

	}

	public MouseListener ratonListener(JButton b) {
		MouseListener ml = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (b.getName().equals("=")) {
					textoStr = sb;
					Server s = new Server();
					Cliente c = new Cliente();
					texto.setText(sb.toString());
				}
				else if (b.getName().equals("+") || b.getName().equals("-") || b.getName().equals("*") || b.getName().equals("/") || b.getName().equals("R")) {
					sb.append(b.getName());
					texto.setText(sb.toString());
				} else {
					sb.append(b.getName());
					texto.setText(sb.toString());
				}
				revalidate();
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

		};
		return ml;
	}
}

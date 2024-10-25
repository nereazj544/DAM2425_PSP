package T3_ProgComunRed.Ejercicios.ServidorAritmetico_new;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainClient extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainClient() {
		super("Calculator Client");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new Keyboard());
		pack();
		setLocationRelativeTo(null);
	}
	
	void start() {
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new MainClient()::start);
	}

}
package T3_ProgComunRed.Ejercicios.ServidorAritmetico;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class Keyboard extends JPanel {

	private static final long serialVersionUID = 1L;
	private Font font;
	static String linea = "";
	public static ButtonGroup grupo = new ButtonGroup();
	
	
	private Display display = new Display();

	public Keyboard() {
		try {
			font = Font.createFont(Font.PLAIN, Keyboard.class.getResourceAsStream("\\res\\code.otf")).deriveFont(50f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30), getBorder()));
		setBackground(Color.WHITE);
		GridBagConstraints constraints = new GridBagConstraints();
		addKey(display, 0, 0, 4, 1, constraints);
		addKey(new ClearKey(), 0, 1, 2, 1, constraints);
		addKey(new UnaryOperatorKey("\u00b1", a -> -a), 2, 1, 1, 1, constraints);
		addKey(new UnaryOperatorKey("\u221a", a -> Math.sqrt(a)), 3, 1, 1, 1, constraints);
		addKey(new NumberKey("1"), 0, 2, 1, 1, constraints);
		addKey(new NumberKey("2"), 1, 2, 1, 1, constraints);
		addKey(new NumberKey("3"), 2, 2, 1, 1, constraints);
		addKey(new BinaryOperatorKey("\u00f7", (a, b) -> a / b), 3, 2, 1, 1, constraints);
		addKey(new NumberKey("4"), 0, 3, 1, 1, constraints);
		addKey(new NumberKey("5"), 1, 3, 1, 1, constraints);
		addKey(new NumberKey("6"), 2, 3, 1, 1, constraints);
		addKey(new BinaryOperatorKey("\u00d7", (a, b) -> a * b), 3, 3, 1, 1, constraints);
		addKey(new NumberKey("7"), 0, 4, 1, 1, constraints);
		addKey(new NumberKey("8"), 1, 4, 1, 1, constraints);
		addKey(new NumberKey("9"), 2, 4, 1, 1, constraints);
		addKey(new BinaryOperatorKey("-", (a, b) -> a - b), 3, 4, 1, 1, constraints);
		addKey(new NumberKey("0"), 0, 5, 1, 1, constraints);
		addKey(new DecimalKey(), 1, 5, 1, 1, constraints);
		addKey(new UnaryOperatorKey("=", a -> a), 2, 5, 1, 1, constraints);
		addKey(new BinaryOperatorKey("+", (a, b) -> {return a + b;}), 3, 5, 1, 1, constraints);
		
//		try {
//			codeCliente();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	private void addKey(JComponent component, int x, int y, int width, int height, GridBagConstraints constraints) {
		constraints.gridwidth = width;
		constraints.gridheight = height;
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		component.addMouseListener((MouseListener) new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					codeCliente(e);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		add(component, constraints);
		grupo.add((AbstractButton) component); //FIXME MAYBE INNECESARIO???
	}
	
	private void codeCliente(MouseEvent e) throws UnknownHostException, IOException {
		
		String line = e.getComponent().getName();
		
		switch (line) {
		case "1", "2", "3", "4", "5", "6", "7", "8", "9", "0": 
			break;
		case "\u00b1", "\u221a", "=": 
			break; 
		case "+", "-", "\u00f7", "\u00d7": 
			break;
		default:
			break;
		}
		
//		BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));
//		String line;
//		System.out.print("> ");
//		while ((line = keyboardIn.readLine()) != null) {
//			Socket socket = new Socket("localhost", 9999);
//			try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
//					PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {
//				out.println(linea);
//				out.flush();
//				System.out.println(in.readLine());
//				System.out.println("> ");
//			}
//		}
	}

	private abstract class Key extends JButton {

		protected static final long serialVersionUID = 1L;
		
		private Key(String text) {
			super(text);
			setFont(font);
			setBorder(BorderFactory.createCompoundBorder(getBorder(), BorderFactory.createEmptyBorder(20, 20, 20, 20)));
			addActionListener(this::update);
		}
		
		protected void update(ActionEvent e) {
			linea += e.getSource().getClass().getName();
		}
	}
	
	private class ClearKey extends Key {

		private static final long serialVersionUID = 1L;
		
		public ClearKey() {
			super("C");
		}
		
		protected void update(ActionEvent e) {
			//TODO CAMBIAR
			//display.clear();
			
			
		}
		
	}
	
	private class NumberKey extends Key {

		private static final long serialVersionUID = 1L;
		private int value;
		
		public NumberKey(String number) {
			super(number);
			value = Integer.parseInt(number);
		}
		
		protected void update(ActionEvent e) {
			//TODO CAMBIAR
			display.update(value);
		}

	}
	
	private class BinaryOperatorKey extends Key {

		private static final long serialVersionUID = 1L;
		private BinaryOperator<Double> operation;
		
		public BinaryOperatorKey(String symbol, BinaryOperator<Double> operation) {
			super(symbol);
			this.operation = operation;
		}
		
		protected void update(ActionEvent e) {
			//TODO CAMBIAR
			display.binaryOperation(operation);
		}
		
	}
	
	private class UnaryOperatorKey extends Key {

		private static final long serialVersionUID = 1L;
		private UnaryOperator<Double> operation;
		
		public UnaryOperatorKey(String symbol, UnaryOperator<Double> operation) {
			super(symbol);
			this.operation = operation;
		}
		
		protected void update(ActionEvent e) {
			//TODO CAMBIAR
			display.unaryOperation(operation);
		}
		
	}
	
	private class DecimalKey extends Key {

		private static final long serialVersionUID = 1L;
		
		public DecimalKey() {
			super(",");
		}
		
		protected void update(ActionEvent e) {
			//TODO CAMBIAR
			display.startDecimal();
		}
		
	}
}

package T2_ProgMultiH.Ejercicios.BarberoDormilon_Swing;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static Queue<JTextField> AsientosQueue = new LinkedList<>();
	public static Queue<JTextField> CalleQueue = new LinkedList<>();
	public static JTextField barbero, afeitado;
	public static JTextArea jarea;
	
	Main(String[] args) {
		super("Barbero Dormilon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout()); 
		GridBagConstraints constraints = new GridBagConstraints();
		
		JPanel panel = new JPanel();
		panel.setName("Barberia_Asiento");
		panel.setPreferredSize(new Dimension(100, 150));
		panel.setBorder(BorderFactory.createTitledBorder("Asientos"));
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		
		for (int i = 0; i<5; i++) {
			JTextField jtext = new JTextField();
			jtext.setEditable(false);
			jtext.setName("Asiento " + (i+1));
			panel.add(jtext);
			AsientosQueue.add(jtext);
		}
		
		contentPane.add(panel);
		
		
		
		panel = new JPanel();
		panel.setName("Barberia_Afeitar");
		panel.setPreferredSize(new Dimension(100, 150));
		panel.setBorder(BorderFactory.createTitledBorder("Afeitar"));
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		
		barbero = new JTextField("Despierto");
		barbero.setEditable(false);
		panel.add(barbero);
		
		afeitado = new JTextField("");
		afeitado.setEditable(false);
		panel.add(afeitado);
		
		contentPane.add(panel);
		
		
		panel = new JPanel();
		panel.setName("Barberia_Calle");
		panel.setPreferredSize(new Dimension(200, 150));
		panel.setBorder(BorderFactory.createTitledBorder("Calle"));
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		
//		for (int i = 0; i<10; i++) {
//			JTextField jtext = new JTextField();
//			jtext.setEditable(false);
//			jtext.setName("Calle " + (i+1));
//			panel.add(jtext);
//			CalleQueue.add(jtext);
//		}
		
		jarea = new JTextArea("");
		jarea.setEditable(false);
		
		panel.add(jarea);
		
		contentPane.add(panel);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	// METODOS Y MAIN /////////////////////////////////////////////////////////////////////////////
	
	public static void cambiarSitio(Cliente cliente, JTextField viejo, JTextField nuevo) {
        viejo.setText("___");
        nuevo.setText(cliente.getName());
        viejo.revalidate();
        nuevo.revalidate();
    }

    public static void cambiarEstado(Barbero barb) {
        if (barb.dormido) barbero.setText("DORMIDO");
        else barbero.setText("DESPIERTO");
        barbero.revalidate();
    }

    public static JTextField getAsiento(Cliente c) {
    	JTextField asiento = AsientosQueue.poll();
    	asiento.setText(c.getName());
    	return asiento;
    }
    
    public static void setAsiento(Cliente c) {
    	JTextField asiento = c.asiento;
    	asiento.setText("");
    	AsientosQueue.add(asiento);
    	c.asiento = null;
    }
    
//    public static JTextField getCalle() {
//    	return CalleQueue.poll();
//    }

    public static JTextField getBarb() {
        return barbero;
    }

    public static JTextField getAfeitado() {
        return afeitado;
    }

	public static void main(String[] args) throws IOException, InterruptedException {
		Barberia barberia = new Barberia(10, 5);
		SwingUtilities.invokeLater(() -> new Main(args).setVisible(true));
		barberia.start();
	}

	
}

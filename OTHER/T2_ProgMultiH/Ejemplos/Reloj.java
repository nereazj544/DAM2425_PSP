package T2_ProgMultiH.Ejemplos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Reloj extends JFrame implements Runnable {
	
	private static final long serialVersionUID = 1L;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	private JLabel hora;
	
	public Reloj() {
		super("Reloj");
		hora = new JLabel(formatter.format(LocalDateTime.now())); //crea jlabel en el que estara el texto
		hora.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(70, 70, 70, 70), hora.getBorder())); //borde para que no este pegado a un lado de la ventana
		hora.setFont(hora.getFont().deriveFont(30f)); //cambia tamaño fuente
		hora.setHorizontalAlignment(JLabel.CENTER); //centrar
		getContentPane().add(hora); //añadir jlabel al frame principal
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //cambiar x a exit
		pack();
		setLocationRelativeTo(null); //"ejecutar"
	}
	
	@Override
	public void run() {
		Runnable actualizarHora = new Runnable() {
			public void run() {
				hora.setText(formatter.format(LocalDateTime.now())); //actualiza el texto a la hora actual
			}
		};
		while (true) {
			SwingUtilities.invokeLater(actualizarHora); //espera a finalizar tasks distintas antes de ejecutar esta
			try {
				Thread.sleep(1000); //espera un segundo antes de volver a actualizar
			} catch (InterruptedException e) {}
		}
	}
	
	private void iniciar() {
		setVisible(true);
		new Thread(this /*ESTE THIS ES UN RUNNABLE es referencia a la linea 52*/, "segundero").start(); //crea thread segundero 
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() {
				new Reloj().iniciar();
			}
		});
	}
	
// ACTIVIDAD 3
/*
se ejecutan tres hilos
- AWT-EventQueue-0
	gestiona eventos, se usa sobre todo para GUI
	cuando detecta un evento, lanza una notificacion 
- DestroyJavaVM (previamente main?)
	previamente main, espera instrucciones para terminate el proceso
- segundero
	thread creada por nosotros, hace que se actualice el reloj cada segundo
 */
	
/*
	
	espera a que se ejecute la thread que esta currently usando las variables, y una vez eso 
		quede libre, la thread invokeLater empieza a usar los archivos???
	no ejecuta los invokelaters de otras cosas debidos a que son de distintas threads (???
 */
	
/*
mirar Ejercicios.Actividad3
 */
}

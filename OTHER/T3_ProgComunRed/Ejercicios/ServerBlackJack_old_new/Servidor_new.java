package T3_ProgComunRed.Ejercicios.ServerBlackJack_old_new;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Base64;
import java.util.List;

import org.fp.dam.naipes.blackjack.Blackjack;
import org.fp.dam.naipes.blackjack.BlackjackPedirException;
import org.fp.dam.naipes.blackjack.BlackjackPlantarseException;
import org.fp.dam.naipes.blackjack.BlackjackRepartirException;

public class Servidor_new implements Runnable {

	Socket socket;
	String hashPlayer;

	public Servidor_new(Socket socket) throws SocketException {
		socket.setSoTimeout(10000);
		this.socket = socket;
	}

	@Override
	public void run() {
		Blackjack bj = new Blackjack();
		try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {

			// FIXME maybe clase con lista de hash??? para hacer servers de un solo uso (?
			String line = "vacio"; 
			//FIXME cambiar while
			while (true) { // FIXME se cierra tras una iteracion
				System.out.println("principio while");
				System.out.println(line);
				line = in.readLine();
				System.out.println("tras in.read " + line);
				
				String[] reLine = line.split(":|#");
				for (int i = 0; i< reLine.length; i++) {
					System.out.println(reLine[i]);
				}

				System.out.println("antes del switch");
				switch (reLine[0]) {
				case "nueva": //INICIA UNA PARTIDA
					String hash = reLine[1] + socket.getInetAddress().getHostAddress();
					String coded = Base64.getEncoder().encodeToString(hash.getBytes());
					hashPlayer = coded; //TODO replace coded
					System.out.println(coded);
					break;
					// SI NO PONGO EL BREAK, SALTA A REPARTIR
				case "repartir": //INICIA UNA PARTIDA
					bj.repartir();
//					System.out.println("pito");
//					out.println("pito"); 
//					out.flush();
//					System.out.println("pito2");
					System.out.println(bj.toString());
					break;
				case "pedir": //solo finaliza si se pierde el juego
					bj.pedir();
//					out.print(bj);
					System.out.println(bj.toString());
					break;
				case "plantarse": //FINALIZA UNA PARTIDA
					bj.plantarse();
//					out.print(bj);
					System.out.println(bj.toString());
					break;
					//SI NO PONGO BREAK SALTA A FIN
				case "fin": //FINALIZA PARTIDA Y CONEXION
					System.out.println("FIN");
					break;
				default:
					System.err.println("ERROR: no se ha introducido un comando conocido, int�ntelo de nuevo");
					break;
				}
				out.println(bj.manoEnCurso());
				String[] ret = bj.toString().split("\n");
				for (int i = 0; i < ret.length; i++) {
					out.println(ret[i]);
				}
				String s = null;
				out.println(s);
				out.flush();
//				out.close();
				System.out.println(bj.manoEnCurso());
			}
			
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
//			System.err.println("TIMEOUT: " + e.getLocalizedMessage() + "(" + socket.getInetAddress() + ")");
		} catch (IOException e) {
			e.printStackTrace();
//			System.err.println("ERROR: " + e.getLocalizedMessage() + "(" + socket.getInetAddress() + ")");
		} catch (BlackjackRepartirException e) {
			System.err.println("ERROR: Ya hay una mano en curso");
		} catch (BlackjackPedirException | BlackjackPlantarseException e) { //ambos errores surgen por la misma razon
			System.err.println("ERROR: no hay una mano en curso");
		}
	}

}
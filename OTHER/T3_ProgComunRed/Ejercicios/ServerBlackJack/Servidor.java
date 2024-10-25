package T3_ProgComunRed.Ejercicios.ServerBlackJack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.fp.dam.naipes.blackjack.Blackjack;
import org.fp.dam.naipes.blackjack.BlackjackPedirException;
import org.fp.dam.naipes.blackjack.BlackjackPlantarseException;
import org.fp.dam.naipes.blackjack.BlackjackRepartirException;

public class Servidor implements Runnable {

	Socket socket;
	String hashPlayer;
	static Blackjack bj;

	public Servidor(Socket socket) throws SocketException {
		socket.setSoTimeout(60000); // 1min
		this.socket = socket;
	}

	@Override
	public void run() {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {

			String line = in.readLine();
			String[] reLine = line.split(":|#");
			hashPlayer = reLine[1];
			bj = (MainServer.mapa.containsKey(hashPlayer)) ? MainServer.mapa.get(hashPlayer) : null;

			try {
				switch (reLine[0].toLowerCase().strip()) {
				case "nueva": // NO INICIA UNA PARTIDA
					crearHash(reLine[1] + socket.getInetAddress().getHostAddress());
					System.out.println("NUEVO USUARIO - " + hashPlayer);
					out.println("OK#" + hashPlayer);
					MainServer.mapa.put(hashPlayer, new Blackjack());
					break; // quitar este break para que automaticamente empieze partida

				case "repartir": // SIEMPRE INICIA UNA PARTIDA
					if (!bj.manoEnCurso()) {
						System.out.println("REPARTIR - " + hashPlayer);
						bj = MainServer.mapa.get(hashPlayer);
						bj.repartir();
						imprimir(out);
					} else
						imposible(out);
					break;

				case "pedir": // A VECES FINALIZA
					if (bj.manoEnCurso()) {
						System.out.println("PEDIR - " + hashPlayer);
						bj = MainServer.mapa.get(hashPlayer);
						bj.pedir();
						imprimir(out);
					} else
						imposible(out);
					break;

				case "plantarse": // FINALIZA UNA PARTIDA
					if (bj.manoEnCurso()) {
						System.out.println("PLANTARSE - " + hashPlayer);
						bj = MainServer.mapa.get(hashPlayer);
						bj.plantarse();
						imprimir(out);
					} else
						imposible(out);
					break;

				case "fin", "salir": // FINALIZA PARTIDA Y USER 
					// tambien hay opcion "salir" para que funcione la app
					System.out.println("FIN - " + hashPlayer);
					out.print("FINOK#" + hashPlayer);
					MainServer.mapa.remove(hashPlayer);
					break;

				default:
					out.println("ERROR: no se ha introducido un comando conocido, itentelo de nuevo");
					System.err.println("ERROR: Comando incorrecto - " + hashPlayer);
					break;
				}
			} catch (BlackjackRepartirException e) {
				out.println("ERROR: Ya hay una mano en curso");
				System.err.println("ERROR: Mano en curso - " + hashPlayer);
			} catch (BlackjackPedirException | BlackjackPlantarseException e) { // ambos errores surgen por la misma razon
				out.println("ERROR: no hay una mano en curso");
				System.err.println("ERROR: Mano en curso - " + hashPlayer);
				System.err.println("ERROR: No hay mano - " + hashPlayer);
			}
			out.flush();

		} catch (SocketTimeoutException e) {
			System.err.println("TIMEOUT: " + e.getLocalizedMessage() + "(" + socket.getInetAddress() + ")");
		} catch (IOException e) {
			System.err.println("ERROR: " + e.getLocalizedMessage() + "(" + socket.getInetAddress() + ")");
		}
	}

	public void imprimir(PrintWriter out) {
		System.out.println(bj);
		String[] split = bj.toString().split("\n");
		for (int i = 0; i < split.length; i++)
			if (!split[i].isBlank())
				out.println(split[i]);
		MainServer.mapa.put(hashPlayer, bj);
	}

	public void crearHash(String hash) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-224");
			md.update(hash.getBytes());
			hashPlayer = Base64.getEncoder().encodeToString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public void imposible(PrintWriter out) {
		if (!bj.manoEnCurso()) {
			out.print("No hay una mano en curso");
			System.err.println("ERROR: No hay mano - " + hashPlayer);
		}
		else {
			out.print("Ya hay una mano en curso");
			System.err.println("ERROR: Mano en curso - " + hashPlayer);
		}
	}

}
package T3_ProgComunRed.Ejercicios.ServerBlackJack_old;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Base64;

// REFERENCIA A LIBRERIA ((necesario para ejercicio))
import org.fp.dam.naipes.blackjack.*;

public class Pruebas {
	public static void main(String[] args) {
		encriptar();
		System.out.println();
		partidaBJ();
	}

	public static void partidaBJ() {
		Blackjack bj = new Blackjack();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		System.out.println(bj.toString());
		while (true) {
			System.out.println(bj.manoEnCurso());
			try {
				switch (br.readLine()) {
				case "rep":
					bj.repartir();
					break;
				case "ped":
					bj.pedir();
					break;
				case "pla":
					bj.plantarse();
					break;
				default:
					break;
				}
			} catch (IOException | BlackjackRepartirException | BlackjackPedirException
					| BlackjackPlantarseException e) {
				System.err.println(e.getMessage());
//				e.printStackTrace();
			}
//			System.out.println(bj.toString());
			//no es necesario este .toString, con imprimir el objeto basta
			System.out.println(bj);
		}

	}

	public static void encriptar() {
		try {
			String hash = "nickname" + InetAddress.getLocalHost().getHostAddress();
			System.out.println(hash);
			String coded = Base64.getEncoder().encodeToString(hash.getBytes());
			System.out.println(coded);
			String decoded = Base64.getDecoder().decode(coded.getBytes()).toString();
			System.out.println(decoded);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

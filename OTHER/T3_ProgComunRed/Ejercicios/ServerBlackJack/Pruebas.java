package T3_ProgComunRed.Ejercicios.ServerBlackJack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// REFERENCIA A LIBRERIA ((necesario para ejercicio))
import org.fp.dam.naipes.blackjack.*;

public class Pruebas {
	public static void main(String[] args) {
//		encriptar();
//		System.out.println();
//		partidaBJ();
		
		Blackjack bj = new Blackjack();
		try {
			bj.repartir();
		} catch (BlackjackRepartirException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> list = Arrays.asList(bj.toString().split("\n"));
		Pattern p = Pattern.compile("(?:K|Q|J|A|[0-9]+)(?:♠|♥|♦|♣)|\\*\\*");
		list.forEach(n -> {
			System.out.println((p.matcher(n).find()));;
			System.out.println(n);
		});
		
//		List<String> a = new ArrayList<>();
//		a.add("a");
//		a.add("a");
//		a.add("a");
//		a.add("a");
//		a.add("a");
//		a.add("a");
//		a.add("a");
//		System.out.println(Arrays.toString(a.toArray()));
		
//		String string = "**2♠3♦6♠";
//		Pattern p = Pattern.compile("(?:K|Q|J|A|[0-9]+)(?:♠|♥|♦|♣)|\\*\\*");
//            if (string != null) {
//                Matcher matcher = p.matcher(string);
//                
//                Set<String> matches = new HashSet<>();
//				while (matcher.find()) {
//					matches.add(matcher.group());
//				}
//				matches.forEach(s -> System.out.println(s));
                
//                MatchResult mr = matcher.toMatchResult();
//                if (matcher.find())
//               	 System.out.println(mr.groupCount());
//                
//                String[] matches = new String[mr.groupCount()];
//                
//                for (int j = 0; j<mr.groupCount(); j++)
//                    if (mr.group(j) != null)
//                        matches[j] = mr.group(0);
//                System.out.println(Arrays.toString(matches));
//            }
             
//		String[] nums = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
//		String[] suits = { "♠", "♥", "♦", "♣" };
//		String[] letters = { "s", "h", "d", "c" };
//
//		for (int i = 0; i < nums.length; i++)
//			for (int j = 0; j < suits.length; j++)
//				System.out.println("mapa.put(\"" + nums[i] + suits[j] + "\", R.drawable." + letters[j] + nums[i].toLowerCase() + ");");

//		for (int i = 0; i<nums.length; i++) {
//			for (int j = 0; j<suits.length; j++) {
//				System.out.println("case \"" + nums[i] + suits[j] + "\":\n" + "\timg.setImageResource(R.drawable." + letters[j] + nums[i].toLowerCase() + ");\n" + "break;");
//			}
//		}

//		String s = "hola";
//		System.out.println(s);
//		s = s.substring(0, s.length()-1);
//		System.out.println(s);
	}

	public static void partidaBJ() {
		Blackjack bj = new Blackjack();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		System.out.println(bj.toString());
		while (true) {
//			System.out.println(bj.manoEnCurso());
			try {
				switch (br.readLine()) {
				case "repartir":
					bj.repartir();
					break;
				case "pedir":
					bj.pedir();
					break;
				case "plantarse":
					bj.plantarse();
					break;
				default:
					break;
				}

				Pattern p = Pattern.compile("(?:K|Q|J|A|[0-9]+)(?:♠|♥|♦|♣)|\\*\\*");
				String[] split = bj.toString().split("\n");
				for (int i = 0; i < split.length; i++) {
					Matcher m = p.matcher(split[i]);
					Set<String> matches = new HashSet<>();
					while (m.find()) {
						matches.add(m.group());
					}
					matches.forEach(s -> System.out.println(s));
				}

				System.out.println(bj);
			} catch (IOException | BlackjackRepartirException | BlackjackPedirException
					| BlackjackPlantarseException e) {
				System.err.println(e.getMessage());
			}
//			System.out.println(bj.toString());
			// no es necesario este .toString, con imprimir el objeto basta
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

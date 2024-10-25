package T3_ProgComunRed.Ejercicios.BlackJack_Profe;

import org.fp.dam.naipes.blackjack.Blackjack;

public class Main {
	public static void main(String[] args) throws Exception {
		Blackjack bj = new Blackjack();
		bj.repartir();
		System.out.println(bj);
		bj.pedir();
		System.out.println(bj);
	}
}

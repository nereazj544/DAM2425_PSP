package T3_ProgComunRed.Ejercicios.ServerBlackJack;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.fp.dam.naipes.blackjack.Blackjack;

public class MainServer {

	public static Map<String, Blackjack> mapa;

	public static void main(String[] args) throws IOException {
		mapa = new HashMap<>();
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("BJ Server listening on port 9999");
		while (true) {
			executorService.submit(new Servidor(serverSocket.accept()));
		}

	}
}

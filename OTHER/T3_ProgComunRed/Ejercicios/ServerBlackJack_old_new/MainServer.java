package T3_ProgComunRed.Ejercicios.ServerBlackJack_old_new;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.fp.dam.naipes.blackjack.Blackjack;

public class MainServer {
	
	public static Map<String, Blackjack> mapa;
	
	public static void main(String[] args) throws IOException {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("BJ Server listening on port 9999");
		while (true) {
			
			executorService.submit(new Servidor_new(serverSocket.accept()));
//			executorService.submit(new Servidor(serverSocket.accept()));
		}
	
	}
}

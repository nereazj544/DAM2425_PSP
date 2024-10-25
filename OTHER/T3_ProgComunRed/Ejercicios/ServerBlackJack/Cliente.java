package T3_ProgComunRed.Ejercicios.ServerBlackJack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class Cliente {

	static String hashPlayer;

	public static void main(String[] args) throws UnknownHostException, IOException {
		while (true) {
			BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));
			String line;
			System.out.print("> ");
			Socket socket = new Socket("localhost", 9999);
			try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {
				line = keyboardIn.readLine();
				if (!line.contains("nueva")) {
					line += "#" + hashPlayer;
				}
				out.println(line);
				out.flush();

				String s;

				if (line.contains("nueva")) {
					hashPlayer = in.readLine();
					System.out.println(hashPlayer);
					String[] split = hashPlayer.split("#");
					hashPlayer = split[1];
				}

				while ((s = in.readLine()) != null) {
					System.out.println(s);
				}
			}
		}
	}
}

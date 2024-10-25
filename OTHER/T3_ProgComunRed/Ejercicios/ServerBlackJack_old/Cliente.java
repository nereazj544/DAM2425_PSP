package T3_ProgComunRed.Ejercicios.ServerBlackJack_old;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class Cliente {
	public static void main(String[] args) throws UnknownHostException, IOException {
		BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));
		String line;
		System.out.print("> ");
		Socket socket = new Socket("localhost", 9999);
//			while ((line = keyboardIn.readLine()) != "fin") {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {
			while (true) {
				line = keyboardIn.readLine();
				out.println(line);
				out.flush();

//				System.out.println(in.readLine());
				String s;
				for (int i = 0; i < 3; i++) {
//				while ((s = in.readLine()) != "\n" || (s = in.readLine()) != null) {
					s = in.readLine();
					if (s != null || s.strip() != "")
						System.out.println(s);
				}
				// TODO QUE TE DEVUELVA TODOS
				System.out.print("> ");
			}
//			}
		}
	}
}

package T3_ProgComunRed.Ejercicios.ServerBlackJack_old_new;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class Cliente_new {
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
				
				String s;
				while (!(s = in.readLine()).equals(null)) {
					System.out.println(s);
				}
				
				
				
				
//				String b = in.readLine();
//				System.out.println(b);
				
//				int times = 2;
//				if (b.equals("false")) {
//					times = 3;
//				}
				
//				System.out.println(in.readLine());
//				boolean manoEnCurso = in.read
//				String s;
//				for (int i = 0; i < times; i++) {
////				while ((s = in.readLine()) != "\n" || (s = in.readLine()) != null) {
//					s = in.readLine();
////					if (s != null || s.strip() != "")
//						System.out.println(s);
//				}
				// TODO QUE TE DEVUELVA TODOS
				System.out.print("> ");
			}
//			}
		}
	}
}

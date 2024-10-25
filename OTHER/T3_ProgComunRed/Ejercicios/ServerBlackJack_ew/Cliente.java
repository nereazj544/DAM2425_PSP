package T3_ProgComunRed.Ejercicios.ServerBlackJack_ew;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class Cliente {
	
	public String hash;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));
//		System.out.print("Introduce tu nickname > ");
		System.out.print("> ");
		String line = keyboardIn.readLine();
		Socket socket = new Socket("localhost", 9999);
//			while ((line = keyboardIn.readLine()) != "fin") {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {
			
			out.println("nueva:" + line);
			in.readLine();
			
			while (true) {
				line = keyboardIn.readLine();
				out.println(line);
				out.flush();
				
//				System.out.println(in.readLine());
//				System.out.println("antes imp");
//				String s;
//				while ((s = in.readLine()) != "") {
//					System.out.println(s);
//				}
//				System.out.println("tras imp");
				//TODO QUE TE DEVUELVA TODOS
				
				System.out.print("> ");
			}
//			}
		}
	}
}

package T5_ProgSegura.Ejercicios.ServerClientHash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashServer implements Runnable {

	static Socket socket;

	public HashServer(Socket socket) throws SocketException {
		socket.setSoTimeout(3000);
		this.socket = socket;
	}

	@Override
	public void run() {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {
			String line = in.readLine();
			System.out.println("línea: " + line);

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(line.getBytes());
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			String hashtext = bigInt.toString(16);
			System.out.println("hash: " + hashtext);

			out.println(hashtext);
			out.flush();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

package T5_ProgSegura.Ejemplos.ServerClientHash;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ServiceTask implements Runnable {
	
	Socket socket;
	InetAddress address;
	
	public ServiceTask(Socket socket) throws SocketException {
		socket.setSoTimeout(3000);
		this.socket = socket;
		address = socket.getInetAddress();
	}

	@Override
	public void run() {
		try (InputStream in = socket.getInputStream(); 
				OutputStream out = socket.getOutputStream()) { 
			MessageDigest md = MessageDigest.getInstance("SHA-224"); //se establece el algoritmo por el que se codifica 
			byte[] buffer = new byte[1024];
			in.read(buffer); //se almacenan los datos en el buffer
				//AL SALIR DE UN STREAM, YA LLEGA LA INFORMACION COMO UN ARRAY DE BYTES, asi que no hace falta hacer nada
			int len;
			System.out.println("antes while");
			while ((len = in.read(buffer)) != -1) { //este bucle va alimentando al buffer byte a byte 
				md.update(buffer, 0, len);
				System.out.println("actualiza");
			}
			System.out.println("fuera while");
			byte[] hash = md.digest();
			System.out.println(address + ": " + Base64.getEncoder().encodeToString(hash));
			out.write(md.digest());
			out.flush();
			System.out.println("fuera flush");
		} catch (SocketTimeoutException e) {
			System.err.println("TIMEOUT: " + e.getLocalizedMessage() + " (" + socket.getInetAddress() + ")");
		} catch (IOException e) {
			System.err.println("ERROR: " + e.getLocalizedMessage() + " (" + socket.getInetAddress() + ")");
		} catch (NoSuchAlgorithmException e) {
			System.err.println("ERROR: " + e.getLocalizedMessage() + " (" + socket.getInetAddress() + ")");
		}
	}

}

package T3_ProgComunRed.Ejercicios.ServidorHora;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("localhost", 9999); 
		System.out.println("CLIENTE: socket creado");
		try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
			System.out.print("CLIENTE: recibida hora local = ");
			System.out.println(in.readLine());
		}
	}
}

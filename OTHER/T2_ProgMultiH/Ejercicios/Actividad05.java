package T2_ProgMultiH.Ejercicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Actividad05 {
	public static void main(String[] args) throws IOException {
		String fichero; 
		int contChars = 0, contWords = 0, contLines = 0;
		if (args.length == 1) 
			fichero = args[0];
		else fichero = pedirRuta();
		try (BufferedReader in = new BufferedReader(new FileReader(fichero))) {
			String linea; 
			linea = in.readLine();
			while (linea != null) {
				contLines++;
				contChars += linea.length();
				contWords += linea.split("\\P{L}+").length;
				linea = in.readLine();
			}
		} 
		System.out.println("Caracteres: " + contChars + "\n" + "Palabras: " + contWords + "\n" + "Lineas: " + contLines);
	}

	static String pedirRuta() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Fichero: ");
		return in.readLine();
	}
}

package T3_ProgComunRed.Ejercicios.ConexionUrlHttp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {

		String urlDir = "https://www.educastur.es/";
		URLConnection urlCon = null;
		String regex = "<img>";
		String regexEnd = "</img>";
		Scanner scanner;
		Pattern pattern = Pattern.compile(regex);

		try (BufferedInputStream in = new BufferedInputStream(new URL("https://www.educastur.es").openStream());
				FileOutputStream fileOutputStream = new FileOutputStream(
						"src\\T3_ProgComunRed\\Ejercicios\\ConexionUrlHttp\\ficheros")) {
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				fileOutputStream.write(dataBuffer, 0, bytesRead);
				Files.copy(in, Paths.get("C:\\Users\\aplod\\Downloads\\prueba"), StandardCopyOption.REPLACE_EXISTING);
			}
			System.out.println("done");
		} catch (IOException e) {
			System.out.println("e");
			// handle exception
		}
		System.out.println("fin");

//		try {
//			URL url = new URL(urlDir);
//			urlCon = url.openConnection();
//			
//			InputStream in = urlCon.getInputStream();
//			BufferedReader br = new BufferedReader(new InputStreamReader(in));
//			scanner = new Scanner(br);
//			scanner.findAll(pattern).forEach(s -> System.out.println(s));
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			System.out.println("finally");
//		}
////		scanner.close();
	}
}

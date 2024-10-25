package T3_ProgComunRed.Ejercicios;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DescargarIMG {
	public static void main(String[] args) throws Exception {
//		String string = "https://www.google.com/";
		String string = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_light_color_272x92dp.png";
		Pattern pattern = Pattern.compile("src=\"(?:\\/(?:\\w+))+.(?:png|jpg|jpeg)\"");
		URL url = new URL(string);
		URLConnection con = url.openConnection();
		
//		try (BufferedInputStream in = new BufferedInputStream(con.getInputStream())) {
////			in.
////			Scanner scanner = new Scanner(in);
////			pattern.matcher(string)
//			Stream<MatchResult> mr = scanner.findAll(pattern);
////			mr.findFirst();
//			String s = scanner.next(mr.findFirst());
//			System.out.println(s);
//			string += s;
//			scanner.close();
//		}
		
		url = new URL(string);
		con = url.openConnection();
		
		try (BufferedInputStream in = new BufferedInputStream(con.getInputStream());
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				BufferedOutputStream fileOut = new BufferedOutputStream(new FileOutputStream(System.getProperty("user.home") + "/Desktop/imagen.png"))) {
			byte[] buffer = new byte[1024];
			int n; 
			while ((n = in.read()) != -1) {
				out.write(n);
			}
			byte[] result = out.toByteArray();
			fileOut.write(result);
		}
	}
}

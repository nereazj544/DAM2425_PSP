package T3_ProgComunRed.Ejercicios.ConexionUrlHttp;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Pattern;

public class c {
	public static void main(String[] args) {
		URL url = null;
		URLConnection urlCon = null;
		
		try {
			Scanner scanner = new Scanner(System.in);
			url = new URL(scanner.nextLine());
			urlCon = url.openConnection();
			
			InputStream is = urlCon.getInputStream();
			scanner = new Scanner(is);
			
			Pattern pattern = Pattern.compile("(:?img *?src)|(:?IMG :?SRC)=\"(.*)\"");
			String linea;
			while (scanner.hasNextLine()) {
				if ((linea = scanner.findWithinHorizon(pattern, 0)) != null) {
					System.out.println(linea);
				}
				scanner.nextLine();
			}
			scanner.close();
		}catch (Exception e) {}
	}
}

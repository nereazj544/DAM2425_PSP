package T5_ProgSegura.Ejemplos.Apuntes.JAAS_Autorizacion;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.PrivilegedAction;

public class EjemploAccion implements PrivilegedAction {

	@Override
	public Object run() {
		File f = new File("fichero.txt");
		if (f.exists()) {
			FileReader fr;
			try {
				fr = new FileReader(f);
				int i;
				System.out.println("Contenido del fichero: ");
				while ((i = fr.read()) != -1)
					System.out.print((char) i);
				fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("El fichero no existe. Creando fichero ... ");
			try {
				FileWriter fw = new FileWriter(f);
				String cadena = "Esto es una linea de texto";
				fw.append(cadena);
				fw.close();
				System.out.println("Fichero creado con datos...");
			} catch (IOException e) {
				System.err.println("Error: " + e.getLocalizedMessage());
			}
		}
		return null;
	}
}

package T2_ProgMultiH.Ejemplos.Progro1_ContadorPalabras;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		if (args.length != 2 || !(args[1].equalsIgnoreCase("c")) || args[1].equalsIgnoreCase("s")) {
			System.out.println("parámetros incorrectos");
			System.exit(-1);
		}
		File[] files = new File(args[0]).listFiles();
		List<File> listFiles = List.of(files)
				.stream()
				.filter(file -> file.isFile())
				.collect(Collectors.toList());
		long t0 = System.nanoTime();
		if (args[1].equalsIgnoreCase("c")) {
			LinkedList<Thread> threadList = new LinkedList<>();
			listFiles.forEach(file -> {
				threadList.add(new Thread(new Contador(file)));
			});
			threadList.forEach(h -> h.start());
			
			threadList.forEach(h -> {
				try {
					h.join();
				} catch (InterruptedException e) {}
			});
		} else {
			listFiles.forEach(file -> new Contador(file).run());
		}
		System.out.println("Tiempo empleado: " + (System.nanoTime()-t0));
	}
}

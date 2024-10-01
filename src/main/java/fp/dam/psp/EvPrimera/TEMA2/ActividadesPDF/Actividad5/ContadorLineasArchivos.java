package fp.dam.psp.EvPrimera.TEMA2.ActividadesPDF.Actividad5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContadorLineasArchivos {
    private static final Pattern pattern = Pattern.compile("\\p{L}+");

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("> Se esperaban 2 parametros por argumentos. Ruta + Modo (S|C)");
            System.exit(0);
        }

        String ruta = args[0];
        String m = args[1].toUpperCase();

        if (!m.equals("S") && !m.equals("C")) {
            System.out.println("> El modo no es el esperado. Se necesita que sea S o C");
            System.exit(0);
        }

        File file = new File(ruta);
        if (!file.isDirectory()) {
            System.out.println("> No has expecificado un directorio valido");
            System.exit(0);
        }

        List<File> fList = new ArrayList<>();
        for (File fl : fList) {
            if (fl.isFile() && fl.getName().endsWith(".txt")) {
                fList.add(fl);
            }
        }
        if (fList.isEmpty()) {
            System.out.println("> No se han encontrado archivos de texto en el dir");
        }

        long inicio = System.currentTimeMillis();
        if (m.equals("S")) {
            S(fList);
        } else {
            C(fList);
        }

        long terminar = System.currentTimeMillis();
        long tiempo = terminar - inicio;
        System.out.println("Tiempo : " + tiempo);

    }

    private static void C(List<File> fList) {
        List<Thread> t = new ArrayList<>();
        for (File f : fList) {
            Thread th = new Thread(() -> Procesar(f));
            t.add(th);
            th.start();
        }
        for (Thread thread : t) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static void S(List<File> fList) {
        for (File file : fList) {
            Procesar(file);
        }
    }

    private static void Procesar(File f) {
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            int l = 0;
            int palabras = 0;
            int c = 0;
            String linea;

            while ((linea = br.readLine()) != null) {
                l++;
                c += linea.length();
                Matcher m = pattern.matcher(linea);
                while (m.find()) {
                    palabras++;
                }
            }

            System.out.println("> Linea: " + linea + "\n > Palabras: " + palabras + "\n > Carcaters: " + c);

        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("> Error en el archivo" + e.getMessage());
        }
    }

}

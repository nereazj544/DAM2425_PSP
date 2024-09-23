package fp.dam.psp.EvPrimera.Tema1_noentraenexamen;

import java.io.IOException;

public class Ejemplo1 {
    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();
        String cmd = "notepad";
        Process p;
        try {
            p = r.exec(cmd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

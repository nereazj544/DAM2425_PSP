package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Enero.Viernes31;

import java.io.IOException;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 6000);

        } catch (IOException e) {
            e.getMessage();
        }
    }
}

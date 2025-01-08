package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Unidad.Ejercicios.Practica3.N;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteHilo implements Runnable {
    private Socket cliS;
    private int numCli;

    public ClienteHilo(Socket cliS, int numero) {
        this.cliS = cliS;
        this.numCli = numero;
    }

    @Override
    public void run() {
        try {

            DataOutputStream out = new DataOutputStream(cliS.getOutputStream());

            String mensjae = "> Cliente numero: " + numCli;
            out.writeUTF(mensjae);
            System.err.println("> Conectado cliente: " + numCli);

            out.close();
            cliS.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

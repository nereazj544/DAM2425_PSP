package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Lunes13.ServidorEcoSocket;

import java.net.Socket;

public class TareaServidor implements  Runnable{
    private  Socket sck;

    public TareaServidor(Socket sck) {
        this.sck = sck;
    }

    @Override
    public void run() {

    }
}

package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.ServidorAgenda.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Agenda implements Runnable {
    private final Socket sck;

    public Agenda(Socket sck) {
        this.sck = sck;
    }

    @Override
    public void run() {
        try(sck;
            DataInputStream in = new DataInputStream(sck.getInputStream());
            DataOutputStream out = new DataOutputStream(sck.getOutputStream())) {


        } catch (Exception e) {

        }

    }
}

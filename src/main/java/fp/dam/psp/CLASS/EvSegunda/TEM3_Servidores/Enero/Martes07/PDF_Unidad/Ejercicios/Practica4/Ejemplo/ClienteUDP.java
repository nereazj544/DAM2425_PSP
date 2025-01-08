package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Unidad.Ejercicios.Practica4.Ejemplo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteUDP {
    public static void main(String[] args) {
        try {

            InetAddress destino = InetAddress.getLocalHost();
            int puerto = 12345;
            byte[] m = "Enviado Salidos !!".getBytes();

            DatagramPacket dp = new DatagramPacket(m, m.length, destino, puerto);
            DatagramSocket sck;

            try {
                sck = new DatagramSocket(34567);
                try {
                    sck.send(dp);

                    System.out.println("> Enviado Datagrama de longitud: " + m.length);
                    System.out.println("> Host Remoto: " + destino.getHostName());
                    System.out.println("> Puero Local: " + sck.getLocalPort());
                    System.out.println("> Puero remoto: " + dp.getPort());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (SocketException e) {
                e.printStackTrace();
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

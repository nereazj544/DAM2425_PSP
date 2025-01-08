package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Unidad.Ejercicios.Practica4_5.Practica5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

public class ServidorUDP_TimeOut {
    public static void main(String[] args) {
        try {
            DatagramSocket sck;
            sck = new DatagramSocket(12345);

            sck.setSoTimeout(5000);

            while (true) {
                try {
                    byte[] buffer = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                    sck.receive(packet);
                    String m = new String(packet.getData(), 0, packet.getLength());

                    if (m.trim().equals("*")) {
                        System.out.println("> Recibido comando de finalizacion");
                        break;
                    }
                    String texMayus = m.toUpperCase();

                    byte[] buEnviar = texMayus.getBytes();
                    DatagramPacket paEnviar = new DatagramPacket(buEnviar, buEnviar.length, packet.getAddress(),
                            packet.getPort());

                    sck.send(paEnviar);
                    System.out.println("> Texto recibido: " + m);
                    System.out.println("> Texto enviado: " + texMayus);

                } catch (SocketTimeoutException e) {
                    System.out.println("> TIEMPO AGOTADO");
                }
            }

            sck.close();
            System.out.println("> Servidor cerrado");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

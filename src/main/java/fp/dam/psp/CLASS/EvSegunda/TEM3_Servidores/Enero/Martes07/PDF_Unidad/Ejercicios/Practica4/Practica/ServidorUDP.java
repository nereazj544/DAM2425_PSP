package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Unidad.Ejercicios.Practica4.Practica;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class ServidorUDP {
    public static void main(String[] args) {
        try {
            byte[] buffer = new byte[1024];
            DatagramSocket sck;

            while (true) {

                sck = new DatagramSocket(12345);
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                sck.receive(packet);
                String m = new String(packet.getData(), 0, packet.getLength());
                
                if (m.trim().equals("*")) {
                    System.out.println("> Recibido comando de finalizacion");
                    break;
                }
                String texMayus = m.toUpperCase();

                byte[] buEnviar = texMayus.getBytes();
                DatagramPacket paEnviar = new DatagramPacket(buEnviar, buEnviar.length, packet.getAddress(), packet.getPort());

                sck.send(paEnviar);
                System.out.println("> Texto recibido: " + m);
                System.out.println("> Texto enviado: " + texMayus);

            }

            sck.close();
            System.out.println("> Servidor cerrado");

        } catch ( IOException e) {
            e.printStackTrace();
        }
    }

}

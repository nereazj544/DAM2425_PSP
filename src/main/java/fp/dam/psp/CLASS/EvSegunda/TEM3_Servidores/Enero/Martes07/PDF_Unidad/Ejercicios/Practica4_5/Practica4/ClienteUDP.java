package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Unidad.Ejercicios.Practica4_5.Practica4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress direccionServidor = InetAddress.getLocalHost();
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                // Leer texto del usuario
                System.out.print("Introduce texto (* para terminar): ");
                String texto = entrada.readLine();

                // Enviar datos
                byte[] bufferEnviar = texto.getBytes();
                DatagramPacket paqueteEnviar = new DatagramPacket(
                        bufferEnviar,
                        bufferEnviar.length,
                        direccionServidor,
                        12345);
                socket.send(paqueteEnviar);

                // Si es asterisco, terminar
                if (texto.trim().equals("*")) {
                    break;
                }

                // Recibir respuesta
                byte[] bufferRecibir = new byte[1024];
                DatagramPacket paqueteRecibir = new DatagramPacket(bufferRecibir, bufferRecibir.length);
                socket.receive(paqueteRecibir);

                // Mostrar respuesta
                String respuesta = new String(paqueteRecibir.getData(), 0, paqueteRecibir.getLength());
                System.out.println("Respuesta del servidor: " + respuesta);
            }

            socket.close();
            System.out.println("Cliente cerrado");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

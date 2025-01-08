package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Unidad.Ejercicios.Practica4.Ejemplo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServidorUDP {
    public static void main(String[] args) {
        byte[] buffer = new byte[1024];
        DatagramSocket sck;

        try {
            sck = new DatagramSocket(12345);
            System.out.println("Esperando Datagrama ..."); 
            DatagramPacket datagrama = new DatagramPacket(buffer, buffer.length); 
            try { 
                sck.receive(datagrama); 
                int bytesRecibidos = datagrama.getLength(); 
                String paquete= new String(datagrama.getData()); 
                System.out.println("Número de Bytes recibidos: " + 
                        bytesRecibidos);  
                System.out.println("Contenido del Paquete : "+ paquete.trim()); 
                System.out.println("Puerto de origen: " + 
                        datagrama.getPort()); 
                System.out.println("IP de origen    : " + 
                        datagrama.getAddress().getHostAddress()); 
                System.out.println("Puerto local:" + sck.getLocalPort()); 
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
            sck.close(); 
        } catch (SocketException e) { 
            e.printStackTrace(); 
        }
    }
}

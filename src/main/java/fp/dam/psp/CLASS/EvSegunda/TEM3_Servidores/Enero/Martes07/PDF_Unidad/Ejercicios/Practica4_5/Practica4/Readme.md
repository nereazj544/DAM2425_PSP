# Codigo Anterior:

```java
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

```

PRACTICA 4
A partir del ejemplo anterior, modifica el cliente para que envíe al servidor líneas de
texto obtenidas a través de la entrada estándar. Modifica el servidor para que
responda devolviendo el texto en mayúsculas o finalice si recibe una línea que
contenga únicamente un asterisco.
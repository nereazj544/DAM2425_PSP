package fp.dam.psp.EvPrimera.tema3.DICIEMBRE.DIA02;

import java.net.InetAddress;

public class IP {
    public static void main(String[] args) {
        try{

        InetAddress l = InetAddress.getLocalHost();
        System.out.println("Host local: " + l.getHostName());
            System.out.println("Host IP local: " + l.getHostAddress());

            InetAddress g = InetAddress.getByName("www.google.es");
            System.out.println("Host local: " + g.getHostName());
            System.out.println("Host local: " + g.getHostName());
            System.out.println("Es alcanzable: " + g.isReachable(5000));



        } catch (Exception e){
            e.getMessage();
        }

    }
}

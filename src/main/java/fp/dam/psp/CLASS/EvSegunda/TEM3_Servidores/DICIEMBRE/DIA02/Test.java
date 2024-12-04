package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.DICIEMBRE.DIA02;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {

    public static void main(String[] args) {
        try {
            prueba(InetAddress.getLocalHost(), "getLocalHost()");
            prueba(InetAddress.getLoopbackAddress(), "getLoopbackAddress()");
            String host = "www.google.com";

            prueba(InetAddress.getByName(host), "getName()" );
            obtener(host);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void prueba(InetAddress dir, String metodos) {
        System.out.println("======================================================");
        System.out.println("Dirección obtenida con el método " + metodos);
        System.out.println("\tMétodo getHostName(): " + dir.getHostName());
        System.out.println("\tMétodo getHostAddress(): " + dir.getHostAddress());
        System.out.println("\tMétodo toString(): " + dir.toString());
        System.out.println("\tMétodo getCanonicalHostName(): " +
                dir.getCanonicalHostName());

    }

    private  static  void obtener(String host){
        System.out.println("===============");
        System.out.println("Direcciones obstenidas con el metodo getAllByName()");
        try {
            InetAddress[] direcciones = InetAddress.getAllByName(host);
            for (InetAddress d: direcciones)
                System.out.println("\t" + d.toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("===============");

    }
}

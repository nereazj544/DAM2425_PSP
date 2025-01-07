package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Unidad.Ejercicios.Practica1;

import java.net.InetAddress;

public class P1 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("> No has introducido una url o una dir. IP");
            System.exit(0);
        }

        try {
            InetAddress dir = InetAddress.getByName(args[0]);

            System.out.println(" == INFORMACION DE LA MAQUINA == ");
            System.out.println("> Nombre del Host: " + dir.getHostName());
            System.out.println("> Direccion IP: " + dir.getHostAddress());
            System.out.println("> Nombre canónico (nombre servidor): " + dir.getCanonicalHostName());

            System.out.println("¿Es alcanceble? " + dir.isReachable(5000));

            System.out.println("¿Es loopback (enviar datos asi mismo)? " + dir.isLoopbackAddress());

        } catch (Exception e) {
            System.out.println("> Error: " + e.getMessage());
        }

    }
}

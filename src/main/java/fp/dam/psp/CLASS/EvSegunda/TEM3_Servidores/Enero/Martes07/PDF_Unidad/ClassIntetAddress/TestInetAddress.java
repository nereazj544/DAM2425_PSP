package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Unidad.ClassIntetAddress;

import java.net.InetAddress;
import java.util.Scanner;

public class TestInetAddress {
    public static void main(String[] args) {
        try {
            // Introduccir el host
            System.out.print("> Insrtar un Host (url): ");
            Scanner sc = new Scanner(System.in);
            String h = sc.nextLine();


            //! Mostrar informacion de diferentes Host
            
            // LocalHost = inf del local
            pruebaMetodos(InetAddress.getLocalHost(), "getLocalHost()"); 

            // LoopbackAddress = inf de la direccion de bucle (127.0.0.1) (O sea, tu mismo)
            pruebaMetodos(InetAddress.getLoopbackAddress(), "getLoopbackAddress()");

            // GetByName = inf de un host especifico
            pruebaMetodos(InetAddress.getByName(h), "getByName()");
            
            obtenerT(h);
            sc.close();


        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Obtiene todas las direcciones IP asociadas a un nombre de host
     * @param h nombre del host ingresado por el usuario
     * 
     */
    private static void obtenerT(String h) {
        System.out.println("=====================================");
        System.out.println("> Dirrecciones obstenidas con el metodo getAllByName()");
        try {
            // Obtener todas las direcciones IP
            InetAddress[] dir = InetAddress.getAllByName(h);

            //Itera las dir obctenidas y las muestra
            for (InetAddress inetAddress : dir) {
                System.out.println("> Nombre del Host: " + inetAddress.getHostName());
                System.out.println("> Direccion IP: " + inetAddress.getHostAddress());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.println("=====================================");
    }

    /**
     * Muestra informacion detallada sobre la dir IP y su host.
     * @param dir informacion del host o IP
     * @param m Nombre del metodo usado
     */

    private static void pruebaMetodos(InetAddress dir, String m) {
        System.out.println("=====================================");

        System.out.println("> Direccion obtenida con el metodo: " + m);
        
        System.out.println("> Nombre del Host ('metodo getHostName()'): " + dir.getHostName());
        
        // Informacion completa
        System.out.println("> ('metodo toString()'): " + dir.toString()); 
        
        // Nombre canonico completo del host ('nombre de dominio completo')
        System.out.println("> ('metodo getCanonicalHostName()'): " + dir.getCanonicalHostName()); 

        System.out.println("=====================================");

    }
}

package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Ejercios.Ej4_ServidorAritmetico.Sin;

import java.io.*;
import java.net.*;
import java.util.*;

public class Cliente {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            Socket socket = new Socket("localhost", 6000);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            System.out.println("> Operaciones dispobibles: ");
            System.out.print(
                    "> Suma \n> Resta \n> Multiplicacion \n> Division \n>Cuadrado  \n> Introduce una operacion: ");

            String operacion = sc.nextLine().toLowerCase();

            out.writeUTF(operacion);

            System.out.println("> Introduce el primer numero: ");
            double n1 = sc.nextDouble();
            out.writeDouble(n1);

            if (!operacion.equals("cuadrado")) {
                System.out.println("> Introduce el segundo numero: ");
                double n2 = sc.nextDouble();
                out.writeDouble(n2);
            }

            String estado = in.readUTF();

            if (estado.equals("ok")) {
                double r = in.readDouble();
                System.out.println("> Resultado: " + r);
            } else {
                System.out.println("> Error en la operacion");
            }

            socket.close();
            sc.close();
            in.close();
            out.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

package fp.dam.psp.EvPrimera.Tema1.ActividadesEntrega.Actividad1;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Escribe un programa Java que lea 2 números de entrada y visualice su suma. 
 * Si los datos introducidos no son numericos, muestra el mensaje 
 * correspondiente en la salida estandar de error. 
 * Crea otro programa Java para ejecutar el anterior y visualizar la salida del mismo.
 */

public class Actividad1 {
    public static void main(String[] args) {
        //Entrada de datos usando Scanner
        Scanner sc = new Scanner(System.in);

        try {
            //Indicamos al usuario lo que queremos que haga
            System.out.println("> Introduce tu primer numero: ");

            double n1 = sc.nextDouble(); //Recogemos los datos

            System.out.println("> Introduce tu segundo numero: ");
            
            double n2 = sc.nextDouble();

            double r = n1 + n2; //Calculamos

            System.out.printf("> El resultado de los numeros: %.2f + %.2f es igual a %.2f%n", n1, n2, r); //Mostramos el resultado de los dos numeros

            //En caso de error
        } catch (InputMismatchException e) {
            System.out.println("> Se ha detectado que no has introduccido un numero.");
        }
        sc.close();

    }
}

package fp.dam.psp.EvPrimera.Tema1_noentraenexamen.Actividades;

import java.util.Scanner;

public class Actividad1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Introduce el primer numero: ");
            double n = sc.nextDouble();
            System.out.println("Introduce el segundo numero: ");
            double n2 = sc.nextDouble();

            double r = n + n2;
            System.out.println("suma: " + r);
        }catch (Exception e){
            System.out.println("Error");
        }
    }
}

package fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad14;

import java.util.Scanner;

public class Parque {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("> Numero de plazas banco");
        System.out.print("> RESPUESTA: ");
        
        int p = sc.nextInt();
        
        System.out.println("> Numero de personas en el parque");
        System.out.print("> RESPUESTA: ");
        int per = sc.nextInt();

        Banco b = new Banco(p);

        for (int i = 0; i < per; i++) {
            new Persona(b).start();
        }
        sc.close();
    }
}

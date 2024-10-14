package fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema1.ActividadesEntrega.Actividad3.A;

/*
 * El programa Java siguiente escribe 5 líneas repitiendo 
 * en cada una de ellas un saludo que recibe a través de un 
 * parámetro en la línea de comandos. ⤵️
 * 
 * Crea y ejecuta otro programa Java que ejecute el 
 * anterior redireccionando su salida estándar a un fichero. 
 * Realiza todo el proceso desde el entorno integrado de desarrollo
 */

public class UnSaludo {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("SE NECESITA UN SALUDO...");
            System.exit(1);
        }
        for (int i = 1; i <= 5; i++)
            System.out.println(i + ". " + args[0]);
    }
}

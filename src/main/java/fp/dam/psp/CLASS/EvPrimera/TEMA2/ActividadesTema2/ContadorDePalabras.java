package fp.dam.psp.CLASS.EvPrimera.TEMA2.ActividadesTema2;



/*
 * Crear un programa que reciba a través de sus argumentos una lista de ficheros de texto y cuente
cuántas líneas, palabras y caracteres hay en cada fichero, así como en el total entre todos los
ficheros. El programa recibirá un argumento adicional que indique si los ficheros se procesarán de
forma secuencial o en paralelo usando hilos.
Se considera como palabra cualquier cadena descrita por la expresión \p{Alpha}+.
El programa medirá el tiempo que emplea en procesar los ficheros. Usar esa información para
comparar la velocidad de proceso de la versión secuencial con la velocidad de proceso de la versión
concurrente.


 */
public class ContadorDePalabras {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("> No se han metido los datos correctos: ");
            System.out.println("> Ruta de los ficheros (carpeta) y modo: 'C' o 'S'");
            System.exit(0);
        }

       
    }

}
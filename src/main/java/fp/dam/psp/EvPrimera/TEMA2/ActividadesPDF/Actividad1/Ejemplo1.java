package fp.dam.psp.EvPrimera.TEMA2.ActividadesPDF.Actividad1;

/*
 * Reproduce el ejemplo 1 y añade comentarios que te ayuden a entenderlo. Después:
• Ejecuta el programa varias veces y comprueba que su salida varía de una ejecución a otra.
• Modifica el ejemplo para que los hilos se creen mediante una clase anónima.
• Partiendo de nuevo del ejemplo original, haz las modificaciones necesarias para que el programa lea de
teclado los parámetros de funcionamiento (número de hilos, número de mensajes que mostrará cada hilo y
tiempos de espera de cada hilo).
• Ejecuta el programa con diferentes valores de los parámetros de funcionamiento y expón tus conclusiones
acerca de la concurrencia en este ejemplo basadas en la observación de las salidas en cada ejecución.
 */

public class Ejemplo1 extends Thread {

    // Constructor que va ha asignar el nombre al hilo y el numero
    public Ejemplo1(int id) {
        super("Hilo " + id);
    }

    // Metodo que se ejecuta cuando el hilo se incie
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(100);
                // Se va a pausar
            } catch (Exception e) {
                // En este caso no saltara un error
            }
            // Se imprime el nombre del hilo más el nº de mensjae
            System.out.println(getName() + ", mensaje " + i);
        }
    }

    // ? Clase Anónima

    public static void main(String[] args) {
        int id = 1;
        new Thread("HILO " + id) {
            @Override
            public void run() {
                for (int j = 1; j <= 5; j++) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                    System.out.println(getName() + ", mensaje " + j);
                }
            }

        }.start();

    }
}

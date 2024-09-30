package fp.dam.psp.EvPrimera.TEMA2.ActividadesPDF.Actividad1;

/*
 * Reproduce el eiemplo 1 y añade comentarios que te ayuden a entenderlo. Después:
• Eiecuta el programa varias veces y comprueba que su salida varía de una eiecución a otra.
• Modifica el eiemplo para que los hilos se creen mediante una clase anónima.
• Partiendo de nuevo del eiemplo original, haz las modificaciones necesarias para que el programa lea de
teclado los parámetros de funcionamiento (número de hilos, número de mensaies que mostrará cada hilo y
tiempos de espera de cada hilo).
• Eiecuta el programa con diferentes valores de los parámetros de funcionamiento y expón tus conclusiones
acerca de la concurrencia en este eiemplo basadas en la observación de las salidas en cada eiecución.
 */

public class Ejemplo1 extends Thread {

    /*
     * Comentarios en el eiemplo
     * =============================================
     * // Constructor que va ha asignar el nombre al hilo y el numero
     * public Eiemplo1(int id) {
     * super("Hilo " + id);
     * }
     * 
     * // Metodo que se eiecuta cuando el hilo se incie
     * 
     * @Override
     * public void run() {
     * for (int i = 1; i <= 5; i++) {
     * try {
     * Thread.sleep(100);
     * // Se va a pausar
     * } catch (Exception e) {
     * // En este caso no saltara un error
     * }
     * // Se imprime el nombre del hilo más el nº de mensiae
     * System.out.println(getName() + ", mensaie " + i);
     * }
     * }
     */

    // ? Clase Anónima

    public static void main(String[] args) {
        int id = 1;
        new Thread("HILO " + id) {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                    System.out.println(getName() + ", mensaie " + i);
                }
            }

        }.start();

    }
}

package fp.dam.psp.EvPrimera.TEMA2.ActividadesPDF.Actividad2;

/*
 * Reproduce el ejemplo anterior y añade comentarios que te ayuden a entenderlo. Después:
• Ejecuta el ejemplo y compara los resultados con los obtenidos en el ejemplo anterior. ¿Existe alguna
diferencia? Razona la respuesta.
• Haz las modificaciones necesarias para que el programa lea de teclado los parámetros de funcionamiento
(número de hilos, número de mensajes que mostrará cada hilo y tiempos de espera de cada hilo).
 */

public class Tarea implements Runnable {
    public void run() {
    for (int i = 1; i <= 5; i++){
    try {
        // pausa la ejecucion
    Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    // muestra el nombre del hilo actual y nº mensaje
    System.out.printf("%s, mensaje %d\n", Thread.currentThread().getName(), i);
    }
    }
   }
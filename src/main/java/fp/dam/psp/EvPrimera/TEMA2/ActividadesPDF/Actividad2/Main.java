package fp.dam.psp.EvPrimera.TEMA2.ActividadesPDF.Actividad2;

public class Main {
    public static void main(String[] args) {
        // Crea una Instancia de la tarea
        Runnable tarea = new Tarea();
        // Crea y incia 3 hilos, que ejecuta la misma tarea
        for (int i = 1; i <= 3; i++)
            new Thread(tarea, "hilo " + i).start();
    }
}
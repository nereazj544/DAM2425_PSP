package fp.dam.psp.EvPrimera.TEMA2.ActividadesPDF.Actividad4;
/*
 * Reproduce el ejemplo 4, añade los comentarios que te ayuden a entenderlo y ejecútalo una vez usando el método 
join y otra más sin usarlo. Comprueba, usando el modo depuración, que en el segundo caso aparece el ya conocido 
hilo “DestroyJavaVM” cuando finaliza el método main y antes de que finalice el hilo “cuenta atrás”. Comprueba 
también que esto no ocurre en el primer caso.
 */

public class Ejemplo4 {
    public static void main(String[] args) throws InterruptedException{

        //Creacion del hilo con una clase anonima.
        Thread h = new Thread("Cuenta atras"){
            @Override
            public void run(){
                //bucle de cuenta atras
                for (int i = 5; i >= 0; i--) {
                    System.out.println(i);
                    try {
                        //el hilo se fue a mimir
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        //se inicia el hilo
        h.start();
        //esperando a que el hilo termine su ejecucion.
        h.join();
        System.out.println("metodo main finalizado");
    }
}

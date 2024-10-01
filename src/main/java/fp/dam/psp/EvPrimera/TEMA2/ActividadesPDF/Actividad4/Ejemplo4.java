package fp.dam.psp.EvPrimera.TEMA2.ActividadesPDF.Actividad4;
/*
 * Reproduce el ejemplo 4, añade los comentarios que te ayuden a entenderlo y ejecútalo una vez usando el método 
join y otra más sin usarlo. Comprueba, usando el modo depuración, que en el segundo caso aparece el ya conocido 
hilo “DestroyJavaVM” cuando finaliza el método main y antes de que finalice el hilo “cuenta atrás”. Comprueba 
también que esto no ocurre en el primer caso.
 */

public class Ejemplo4 {
    public static void main(String[] args) throws InterruptedException{
        Thread h = new Thread("Cuenta atras"){
            @Override
            public void run(){
                for (int i = 5; i >= 0; i--) {
                    System.out.println(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        h.start();
        h.join();
        System.out.println("metodo main finalizado");
    }
}

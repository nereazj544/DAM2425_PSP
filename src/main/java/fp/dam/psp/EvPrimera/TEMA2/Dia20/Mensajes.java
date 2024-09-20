package fp.dam.psp.EvPrimera.TEMA2.Dia20;

public class Mensajes {
    public static void main(String[] args) {
//        HiloMensajes h = new HiloMensajes();
//        HiloMensajes h = new HiloMensajes("n");
//        h.run();

//        Clase anonima
Thread t = new Thread(){
        @Override

        public void run () {
            for (int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                System.out.println(getName() + ", mensaje " + i);
            }
        }

};
t.start();
//        h.start();
    }
}



//public class Mensajes extends  Thread{
//
//    @Override
//    public void run() {
//        for (int i = 1; i <= 5; i++){
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {}
//            System.out.println(Thread.currentThread().getName() + ", mensaje " + i);
//        }
//    }
//
//    public static void main(String[] args) {
        //Referenciando un hilo.
//        Mensajes hilo = new Mensajes();
//        Mensajes hilo2 = new Mensajes();
//        hilo.start(); //Se crea un nuevo hilo
//        hilo.run(); //Hilo principal, no se crea un nuevo hilo
//
//    }
//}

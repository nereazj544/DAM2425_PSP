package fp.dam.psp.CLASS.EvPrimera.ActividadesDeEntrega.Tema2.Noviembre.Dia11;

import java.util.concurrent.Semaphore;

public class Barberia {
    Semaphore salaEspera;
//    Cliente sillon; //* Almacenaje de la ref. del cliente que se siente
    Sillon sillon;

    public Barberia(int sillas){
        salaEspera = new Semaphore(sillas);

    }

//    Supuestamente hay algo mal aqui, en este metodo no hay exclusion mutua
    public boolean enSalaEspera(){
        if (salaEspera.availablePermits() == 0) {
            //? Este metodo retornara verdadero o flaso. Si la sala esta llena retorna falso, si esta vacia (alguna silla) verdaro
            return false;
        }else {
//            try {
//                salaEspera.acquire();
                if (salaEspera.tryAcquire()){
                    return true;
                }
//            } catch (InterruptedException e) {
//               Thread.currentThread().interrupt();
//               System.out.println("PETO :)");
//               e.getMessage();
//               return false;
            }

        return true;
    }

    public void soliCortarPelo(){}
}
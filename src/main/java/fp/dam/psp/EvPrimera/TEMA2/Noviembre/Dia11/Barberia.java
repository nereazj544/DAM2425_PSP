package fp.dam.psp.EvPrimera.TEMA2.Noviembre.Dia11;

import java.util.concurrent.Semaphore;

public class Barberia {
    Semaphore salaEspera;
//    Cliente sillon; //* Almacenaje de la ref. del cliente que se siente
    Sillon sillon;

    public Barberia(int sillas){
        salaEspera = new Semaphore(sillas);

    }

    public boolean enSalaEspera(){
        if (salaEspera.availablePermits() == 0) {
            //? Este metodo retornara verdadero o flaso. Si la sala esta llena retorna falso, si esta vacia (alguna silla) verdaro
            return false;
        }else {
            try {
                salaEspera.acquire();
            } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
               System.out.println("PETO :)");
               e.getMessage();
            }
        }
        return true;
    }

    public void soliCortarPelo(){}
}

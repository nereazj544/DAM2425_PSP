package fp.dam.psp.EvPrimera.TEMA2.Noviembre.Dia11;

import java.util.concurrent.Semaphore;

public class Barberia {
    Semaphore salaEspera;
    Cliente sillon; //* Almacenaje de la ref. del cliente que se siente



    public Barberia(int sillas){
        salaEspera = new Semaphore(sillas);

    }

    public boolean enSalaEspera(){
        //? Este metodo retornara verdadero o flaso. Si la sala esta llena retorna falso, si esta vacia (alguna silla) verdaro
        return false;
    }

    public void soliCortarPelo(){}
}

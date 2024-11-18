package fp.dam.psp.EvPrimera.TEMA2.Noviembre.ACTIVIDADESDEENTREGA.BarberoDormilon;
import java.util.concurrent.Semaphore;
public class Barberia {
    Semaphore salaEspera;
    Sillon sillon;

    public Barberia(int sillas) {
        salaEspera = new Semaphore(sillas);
    }

    public boolean entrarEnSalaDeEspera() {
        if (salaEspera.tryAcquire())
            return false;
        else
            return true;
    }

    public void solicitarCortePelo() {

    }

}

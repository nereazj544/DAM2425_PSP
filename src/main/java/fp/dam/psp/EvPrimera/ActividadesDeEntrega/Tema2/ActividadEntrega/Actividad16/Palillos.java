package fp.dam.psp.EvPrimera.ActividadesDeEntrega.Tema2.ActividadEntrega.Actividad16;

import java.util.concurrent.Semaphore;

public class Palillos {
    Semaphore s = new Semaphore(1);

    public void Tomar() throws InterruptedException {
        s.acquire();
    }

    public void Soltar() {
        s.release();
    }
}

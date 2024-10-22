package fp.dam.psp.EvPrimera.Other.Globos;

import static fp.dam.psp.EvPrimera.Other.Globos.Main.actualizar;

public class Almacen {
    private static int MAX_GB = 10;
    private static int MAX_Hin = 3;
    int a = 0;
    int Gdisponibles = MAX_GB;
    int GHinchadar = 0;

    public synchronized int entregar(String hincha) {
        while (Gdisponibles == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        Gdisponibles--;
        int numGlobo = MAX_GB - Gdisponibles;
        actualizar("GLOBO " + numGlobo + " ENTRAGADO A: " + hincha + "\n");
        return numGlobo;
    }

    public synchronized void Hinchar() {
        while (GHinchadar >= MAX_Hin) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        GHinchadar++;
    }

    public synchronized void EmpHinchar() {
        while (GHinchadar >= MAX_Hin) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        GHinchadar++;
    }

    public synchronized void finHinchar() {
        GHinchadar--;
        notifyAll();
    }

    public synchronized boolean hayGlobosHinchando() {
        return GHinchadar > 0;
    }

    public synchronized boolean hayGlobosDisponibles() {
        return Gdisponibles > 0;
    }

}

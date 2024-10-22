package fp.dam.psp.EvPrimera.Other.Piscina;

import static fp.dam.psp.EvPrimera.Other.Piscina.Main.actualizar;

public class Piscina {

    int cOcupadas = 0;
    int hombre = 0, mujer = 0, niños = 0, niñas = 0, submarinistas = 0;
    int CALLES_TOTALES = 8;

    public synchronized void entrar(String nombre, String tipo) {
        while (cOcupadas == CALLES_TOTALES) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        cOcupadas++;
        incremetarContador(tipo);
        actualizar(nombre + "(" + tipo + ")entra." + obtenerEstadistias() + "\n");
        notify();
    }

    public synchronized void EntrarSubmarinistas(String nombre) {
        while (cOcupadas > CALLES_TOTALES - 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        cOcupadas += 2;
        submarinistas++;
        actualizar(nombre + "(Submarinista) entra. " + obtenerEstadistias() + "\n");
        notify();
    }

    public synchronized void salirNadador(String nombre, String tipo) {
        cOcupadas--;
        decrementarContador(tipo);
        actualizar(nombre + " (" + tipo + ") sale. " + obtenerEstadistias() + "\n");
        notify();
    }

    private String obtenerEstadistias() {
        return String.format("Total: H=%d, M=%d, N=%d, Ñ=%d, S=%d", hombre, mujer, niños, niñas, submarinistas);
    }

    private void incremetarContador(String tipo) {
        switch (tipo) {
            case "Hombre":
                hombre++;
                break;
            case "Mujer":
                mujer++;
                break;
            case "Niño":
                niños++;
                break;
            case "Niña":
                niñas++;
                break;

        }
    }

    private void decrementarContador(String tipo) {
        switch (tipo) {
            case "Hombre":
                hombre--;
                break;
            case "Mujer":
                mujer--;
                break;
            case "Niño":
                niños--;
                break;
            case "Niña":
                niñas--;
                break;

        }
    }

}

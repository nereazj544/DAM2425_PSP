package fp.dam.psp.EvPrimera.Other.Montaje;

import static fp.dam.psp.EvPrimera.Other.Montaje.Main.actualizar;

public class Cadena {
    int[] productos;
    int capacidad;
    int p = 0;
    int totalEm = 0;

    public Cadena(int capacidad) {
        this.capacidad = capacidad;
        this.productos = new int[capacidad];
    }

    public synchronized void colcar(int tipo) throws InterruptedException {
        while (p >= capacidad) {
            wait();
        }

        productos[p++] = tipo;
        actualizar("Producto tipo " + tipo + " colocado en la cadena\n");
        notifyAll();
    }

    public synchronized void retirar(int tipoEs) throws InterruptedException {
        while (true) {
            boolean e=false;
            for (int i = 0; i < productos.length; i++) {
                if (productos[i] == tipoEs) {
                    productos[i] = 0;
                    p--;
                    totalEm++;
                    actualizar("Producto tipo: " + tipoEs + " retirado de la cadena. \n" +
                            "Total empaquetados: " + totalEm + "\n");
                    notifyAll();
                    return;
                }
            }
            if (!e) {
                wait();
            }
        }
    }

}

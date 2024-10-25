package fp.dam.psp.EvPrimera.Other.Barbero;

import static fp.dam.psp.EvPrimera.Other.Barbero.Main.actualizar;

public class Cliente {
    private static int contador = 0;
    private int id;
    private Barberia barberia;

    public Cliente(Barberia barberia) {
        this.id = contador++;
        this.barberia = barberia;
    }

    public int getId() {
        return id;
    }

    public void intentarEn (){
        if (!barberia.entrar(this)) {
            actualizar("Cliente " + id + " se va porque la sal esta llena \n");
        }
    }
}

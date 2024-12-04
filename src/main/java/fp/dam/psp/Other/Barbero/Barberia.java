package fp.dam.psp.Other.Barbero;

import static fp.dam.psp.Other.Barbero.Main.actualizar;

import java.util.*;

public class Barberia {
    int NumSillas;
    Queue<Cliente> espera;
    boolean BarberoOc;
    boolean BarberoDes;

    public Barberia(int numSillas) {
        this.NumSillas = numSillas;
        this.espera = new LinkedList<>();
        this.BarberoDes = false;
        this.BarberoOc = false;
    }

    public synchronized boolean entrar(Cliente cliente) {
        if (espera.size() < NumSillas) {
            espera.offer(cliente);
            actualizar("Cliente " + cliente.getId() + " entra en la sala\n");
            if (BarberoDes) {
                BarberoDes = false;
                notifyAll(); //Si no para es que o es notify o notifyAll
            }
            return true;
        } else {

            actualizar("Cliente" + cliente.getId() + " se va sin ser atendido. \n Sala de espera llena\n");
            return false;
        }
    }

    public synchronized Cliente atenderCliente() throws InterruptedException {
        while (espera.isEmpty()) {
            BarberoDes = true;
            wait();
        }
        BarberoDes = false;
        return espera.poll();
    }

}

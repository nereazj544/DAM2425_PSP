package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Unidad.Ejercicios.Practica3.N;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        final int N = 10;
        int cActu = 0;

        try {
            ServerSocket s = new ServerSocket(6000);
            System.out.println(". . . Servidor iniciando . . . ");
            System.out.println("> Esperdando " + N + " clientes");


            while (cActu < N) {
                Socket sck = s.accept();
                cActu++;

                Thread t = new Thread(new ClienteHilo(sck, cActu));
                t.start();
            }

            s.close();
            System.out.println("> El servidor ha cerrado la conexión. || Maximo de clientes alcanzado.");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}

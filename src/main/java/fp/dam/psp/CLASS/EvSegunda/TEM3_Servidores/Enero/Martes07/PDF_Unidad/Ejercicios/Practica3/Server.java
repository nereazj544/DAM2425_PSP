package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Unidad.Ejercicios.Practica3;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        final int MAX_Cliente = 3;
        int cAc = 0;

        try {
            ServerSocket s = new ServerSocket(6000);
            System.out.println(". . . Servidor iniciando . . . ");



            while (cAc < MAX_Cliente) {
                Socket sk = s.accept();
                cAc++;


                DataOutputStream out = new DataOutputStream(sk.getOutputStream());

                String m = "> Cliente nº " +  cAc;

                out.writeUTF(m);
                System.out.println("> Conectado cliente "  + cAc);

                sk.close();
                out.close();
            }

            s.close();
            System.out.println("> Servidor cerrado || Maxmimo de clientes alcanzado");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package fp.dam.psp.PDFACTIVIDADES.T2.HILOSALTONIVEL.pis.l;

import java.util.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.*;

import fp.dam.psp.Other.Piscina.Usuario;

public class ConL {


private static final Random r = new Random();

    // ! TOTAL DE CALLE & USUARIOS
    static final int C_Tl = 8;
    static final int User_Tl = 20;

    // ? Calle ocupadas
    static int calOcu = 0;

    // ? Usuarios
    private static int mujeres = 0, hombres = 0;
    private static int chavalas = 0, chavales = 0;
    private static int submarinista = 0;


    private static Lock lock = new ReentrantLock();




    static class Usuario extends Thread {

        private String nombre;
        private String tp;
        private int callesNec;

        public Usuario(String nombre, String tp) {
            this.nombre = nombre;
            this.tp = tp;
            if (tp.equals("submarinista")) {
                this.callesNec = 2;
            } else {
                this.callesNec = 1;
            }
        }

        private void nadar() throws InterruptedException {
            sleep(50 + r.nextInt(31));
        }

        private void actuCon(boolean entrado) {
            int f;
            if (entrado) {
                f = 1;
            } else {
                f = -1;
            }

            switch (tp) {
                case "hombre":
                    hombres += f;
                    break;
                case "mujer":
                    mujeres += f;
                    break;
                case "chavales":
                    chavales += f;
                    break;
                case "chavalas":
                    chavalas += f;
                    break;
                case "submarinista":
                    submarinista += f;
                    break;

                default:
                    break;
            }
        }

        @Override
        public void run() {
            try {
                lock.lock();
                
                while (calOcu + callesNec > C_Tl) {
                    lock.unlock();
                    sleep(100);
                    lock.lock();
                }

                calOcu += callesNec;
                actuCon(true);
                System.out.printf("%s entra - H:%d M:%d N:%d Ñ:%d S:%d%n",
                        nombre, hombres, mujeres, chavales, chavalas, submarinista);
                

                nadar();
                lock.lock();
                calOcu -= callesNec;

                actuCon(false);
                System.out.printf("%s entra - H:%d M:%d N:%d Ñ:%d S:%d%n",
                        nombre, hombres, mujeres, chavales, chavalas, submarinista);

                lock.unlock();

            } catch (Exception e) {
                // TODO: handle exception
                interrupt();
                e.getMessage();
            }

        }

    }






    public static void main(String[] args) {
        String[] tipo = { "hombre", "mujer", "chavales", "chavalas", "submarinista" };
        Thread[] usuarios = new Thread[User_Tl];
        for (int i = 0; i < User_Tl; i++) {
            String tps = tipo[r.nextInt(tipo.length)];

            usuarios[i] = new Thread(new Usuario("Usuario" + (i + 1), tps));
            usuarios[i].start();

        }
    }
}

package fp.dam.psp.PDFACTIVIDADES.T2.ActividadesPDF.Actividad1;

import java.util.Scanner;

/**
 * Actividad1
 * • Partiendo de nuevo del ejemplo original, haz las modificaciones necesarias
 * para que el programa lea de
 * teclado los parámetros de funcionamiento (número de hilos, número de mensajes
 * que mostrará cada hilo y
 * tiempos de espera de cada hilo).
 */
public class Actividad1 extends Thread {
    int nM;
    int t;

    public Actividad1(int nM, int t, int id) {
        super("Hilo " + id);
        this.nM = nM;
        this.t = t;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
            System.out.println(getName() + ", mensaje " + i);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("> Nº de hilos");
        int nhilos = sc.nextInt();

        System.out.println("> Nº de mensajes");
        int nm = sc.nextInt();

        System.out.println("> Tiempo de espera");
        int t = sc.nextInt();

        for (int i = 1; i <= nhilos; i++) {
            new Actividad1(nm, t, i).start();
        }
        sc.close();
    }


}
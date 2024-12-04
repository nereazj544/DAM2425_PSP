package fp.dam.psp.PDFACTIVIDADES.T2.ActividadesPDF.Actividad2;

import java.util.Scanner;

public class TareaTeclado implements Runnable {
    int nMen;
    int t;

    public TareaTeclado(int nMen, int t) {
        this.nMen = nMen;
        this.t = t;
    }

    @Override
    public void run() {
        for (int i = 1; i <= nMen; i++) {
            try {
                Thread.sleep(t);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.printf("%s, mensaje %d\n", Thread.currentThread().getName(), i);
            
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

        Runnable tR = new TareaTeclado(nm, t);
        for (int i = 1; i <= nhilos; i++) {
            new Thread(tR, "hilo " + i).start();
        }
        
        sc.close();
    }
}

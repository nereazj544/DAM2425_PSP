package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Febrero.Viernes7.TAREADELSERVIDORSIGNAURE.V2;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class ClienteSignature {

    private String host;
    private int puerto;

    public ClienteSignature(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    public String solFrima(String m) throws Exception {
        try (
                Socket s = new Socket("localhost", 6000);
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());

        ) {
            // String mensaje = "Malibu";

            out.writeUTF(m);

            int lonFirma = in.readInt();
            byte[] fr = new byte[lonFirma];
            in.read(fr);

            boolean v = in.readBoolean();

            String frBS64 = Base64.getEncoder().encodeToString(fr);

            String r;
            if (v) {
                r = "si";
            } else {
                r = "no";
            }

            return "verficado " + r;

            // return "Firma " + frBS64 + " verificado: " + (v ? "si" : "no");

        }
    }

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            ClienteSignature c = new ClienteSignature("localhost", 6000);
            System.out.println("> Mensaje: ");
            String m = sc.nextLine();
            String r;
            r = c.solFrima(m);
            System.out.println("Resultado: " + r);

            sc.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

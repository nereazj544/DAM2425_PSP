package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Ejercios.Ej4_ServidorAritmetico.Sin;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(6000);
            System.out.println(". . . Servidor iniciando . . . ");

            while (true) {
                Socket sck = s.accept();
                DataInputStream in = new DataInputStream(sck.getInputStream());
                DataOutputStream out = new DataOutputStream(sck.getOutputStream());

                String o = in.readUTF();
                double n1 = in.readDouble();
                double r = 0;

                double n2 = 0;

                if (!o.equals("cuadrado")) {
                    n2 = in.readDouble();
                }

                switch (o) {
                    case "suma":
                        r = n1 + n2;
                        break;
                    case "resta":
                        r = n1 - n2;
                        break;
                    case "multiplicacion":
                        r = n1 * n2;
                        break;
                    case "division":
                        if (n2 != 0) {
                            r = n1 / n2;
                        } else {
                            out.writeUTF("division por 0");
                        }

                        break;
                    case "cuadrado":
                        if (n1 >= 0) {
                            r = Math.sqrt(n1);
                        } else {
                            out.writeUTF("numero negativo");

                        }
                        break;
                    default:
                        out.writeUTF("error");
                        break;
                }

                out.writeUTF("ok");
                out.writeDouble(r);

                sck.close();
                in.close();
                out.close();
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}

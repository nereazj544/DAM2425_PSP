package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Viernes10.ServidorEco;

/*
* Ejercicio: 3 Servidor eco
Desarrolla con Java un sistema cliente/servidor en el que el servidor lea secuencias de caracteres
enviadas por el cliente y se las reenvíe inmediatamente de vuelta. Ambas aplicaciones usarán la
consola como dispositivo estándar de E/S.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws  IOException {
        //! Protocolos sin crear. En este ejemplo se crea el protocolo. Cuando se acepte la conexion leelar - escribir - cerrar

//        try {
            ServerSocket s = new ServerSocket(6000); //Numero de puerto
            System.out.println(". . . Servidor iniciando . . . ");

            Socket sck = s.accept(); //Aceptamos la conexion

            BufferedReader br = new BufferedReader(new InputStreamReader(sck.getInputStream()));

        PrintWriter pw = new PrintWriter(sck.getOutputStream(), true);

        String l;

        while ((l = br.readLine()) != null){
            System.out.println("> Recibido: " + l);
            pw.println(l);
            if (l.equals("fin")){
                break;
            }
        }

        br.close();
        pw.close();
        s.close();
//        }catch (IOException e) {
//            e.getMessage();
//        }
    }
}

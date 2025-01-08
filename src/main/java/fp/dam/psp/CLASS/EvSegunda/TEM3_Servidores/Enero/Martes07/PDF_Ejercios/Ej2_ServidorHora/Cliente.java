package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Ejercios.Ej2_ServidorHora;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.Instant;

public class Cliente {

    public static void main(String[] args) {
        try {

            /*
             * ? INSTANT
             * Instant inicio = Instant.now();
             * Se utiliza para obtener una marca de tiempo precisa del momento actual.
             * En este caso específico, se usa para:
             * - Registrar el momento exacto antes de iniciar la conexión con el servidor
             * - Posteriormente, junto con otro `Instant.now()` al final, permite calcular
             * el tiempo total que tardó la comunicación (latencia)
             * - La clase `Instant` es más precisa que otras alternativas para medir tiempo
             * en Java, ya que proporciona una marca temporal con precisión de nanosegundos
             */

            Socket s = new Socket("localhost", 6000);
            DataInputStream in = new DataInputStream(s.getInputStream());

            String hora = in.readUTF();
            System.out.println("> Hora recibida: " + hora);

            in.close();
            s.close();



        } catch (IOException e) {
            // TODO: handle exception
            System.out.println("> Error: " + e.getMessage());
        }
    }
}
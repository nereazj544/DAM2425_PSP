package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Enero.Jueves23.ActidadPDF.Activdad1;

import java.io.*;
import java.net.*;
import java.time.*;

public class RequestTask implements Runnable {
    private final Socket socket;

    public RequestTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (socket;
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
            // Notificar que se ha recibido la conexion
            System.out.println("> Se ha recidbido una conexcion con el: " + socket.getInetAddress().getHostAddress()
                    + " || Puerto utilizado: " + socket.getPort() + " || Hora Acutal: " + LocalDateTime.now());

            while (true) {
                /*
                 * ! Ignorar es algo que siempre se me olvida
                 * ? - toLowerCase() convierte todos los caracteres a minúsculas
                 * ? - toUpperCase() convierte todos los caracteres a mayúsculas
                 */

                // Leer el mensaje
                String m = in.readUTF();

                // mostrar el mensaje
                System.out.println("> Mensaje recibio: " + m);

                // Finalizar en caso que se ponga "fin" (da igual de que forma porque se ba a
                // poner en minusculas) y se cerrara el socket.
                if (m.toLowerCase().equals("fin")) {
                    System.out.println("> Conexion con el cliente finalizada");
                    socket.close();
                }

                // devolver el mensaje.
                out.writeUTF(m);

            }

            //Tiempo de espera agotado
        } catch (SocketTimeoutException e) {
            try (DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
                out.writeUTF("> Error: tiempo de espera agotado. Motivo: " + e.getMessage());
                out.writeUTF("SE HA CERRADO LA CONEXION CON EL SERVIDOR/CLIENTE.");
            } catch (Exception ex) {
                System.out.println("> Se ha producciodo algun error: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("> Se ha producido otro tipo de error: " + e.getMessage());
        }
    }

}

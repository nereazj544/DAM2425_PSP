package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.ActividadesdeEntrega.ClienteSwing;

import java.io.*;
import java.net.*;
import java.time.*;

public class RequestTask implements Runnable {
    final Socket socket;

    public RequestTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (socket;
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());) {
            System.out.println("> Peticion recibida de " + socket.getInetAddress().getHostAddress() + ":"
                    + socket.getPort() + " || " + LocalDateTime.now());

            while (true) {
                String mensaje = in.readUTF();
                System.out.println("> Mensaje recibo: " + mensaje);

                if (mensaje.equals("fin")) {
                    System.out.println("> Finalizado");
                    break;
                }
                out.writeUTF(mensaje);
            }

        } catch (SocketTimeoutException e) {
            try (DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
                out.writeUTF("> Error: Tiempo de espera agotado. Motivo: " + e.getMessage());

            } catch (Exception ex) {
                System.out.println("> Mensaje de error enviado");
            }

        } catch (Exception e) {
            try (DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
                out.writeUTF("Error: " + e.getMessage());
            } catch (IOException ex) {
                System.err.println("Error enviando mensaje de error: " + ex.getMessage());
            }
        } finally {
            System.out.println("Conexión finalizada: " +
                    socket.getInetAddress() + ":" + socket.getPort() +
                    " en " + LocalDateTime.now());
        }
    }

}

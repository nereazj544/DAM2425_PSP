package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Viernes17.v2;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.net.Socket;
import java.time.LocalDateTime;

public class RequestTask implements Runnable {

    private final Socket socket;

    public RequestTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (socket) {

            System.out.println("Petición : " +
                    socket.getInetAddress() + " : " + socket.getPort() +
                    " : " + LocalDateTime.now());

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            try {
                for (; ; )
                    out.writeUTF(in.readUTF());
            } catch (EOFException e) {
                System.out.println("respuesta finalizada : " +
                        socket.getLocalAddress() + " : " + socket.getLocalPort() +
                        " : " + LocalDateTime.now());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }
}

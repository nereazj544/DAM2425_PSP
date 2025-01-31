package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Enero.Viernes31.JulioServer;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.*;





public class RequestHandler implements Runnable {

    private final Socket socket;
    private String clientIP;
    private int clientPort;

    public RequestHandler(Socket socket) {
        this.socket = socket;
        clientIP = socket.getInetAddress().getHostAddress();
        clientPort = socket.getPort();
    }

    @Override
    public void run() {
        try (socket) {
                DataInputStream in = new DataInputStream(socket.getInputStream());
                String rq = in.readUTF();

                if (rq.equals("hash")){
                    MessageDigest md = MessageDigest.getInstance(in.readUTF());

                    byte [] hash = md.digest(rq.getBytes(StandardCharsets.UTF_8));

                }

        } catch (IOException e) {
            error(e.getLocalizedMessage());
        } catch (NoSuchAlgorithmException e) {
            error(e.getLocalizedMessage());
        }
    }

    private void error(String s) {
        System.err.printf("ERROR: %s: %s\n", LocalTime.now(), s);
    }
}

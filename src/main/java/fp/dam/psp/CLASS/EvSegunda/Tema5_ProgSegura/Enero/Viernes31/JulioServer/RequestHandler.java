package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Enero.Viernes31.JulioServer;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.*;
import java.util.Base64;


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

                        byte [] bff =  new byte[1024];
                        int n;

                        while ((n = in.read(bff)) != 1)
                            md.update(bff, 0, n);

                        byte [] hash = md.digest();
//                        byte [] B64 = Base64.getEncoder().encode(hash);
                    String B64 = Base64.getEncoder().encodeToString(hash);

                        //Convertirlo aun string y mandolor con utf
//                    new DataOutputStream(socket.getOutputStream()).writeUTF(new String(B64));
                    new DataOutputStream(socket.getOutputStream()).writeUTF(new String B64 = Base64.getEncoder().encodeToString(hash));

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

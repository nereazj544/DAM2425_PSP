package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Enero.Viernes31;

import fp.dam.psp.Other.Fumadores.Mesa;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;

public class RTask implements Runnable {
    private  final Socket sck;

    public  RTask(Socket sck){
        this.sck = sck;
    }

private  String generarHash(String mensaje) {
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(mensaje.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) sb.append('0');
            sb.append(hex);
        }
        return sb.toString();
    } catch (Exception e) {
        e.getMessage();
        return null;
    }
}



    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(sck.getInputStream());
            DataOutputStream out = new DataOutputStream(sck.getOutputStream());

            System.out.println("> Se ha recidbido una conexcion con el: " + sck.getInetAddress().getHostAddress()
                    + " || Puerto utilizado: " + sck.getPort() + " || Hora Acutal: " + LocalDateTime.now());

        }catch (Exception e){
            enviarmensaje("Se ha detectado un error: " + e.getMessage());
        }

    }


    //* Enviar mensaje de error o otro tipo
    private   void enviarmensaje(String mensaje) {
        try {
            DataOutputStream  out = new DataOutputStream(sck.getOutputStream());
            out.writeUTF(mensaje);
        }catch (Exception e){
            e.getMessage();
        }
    }
}

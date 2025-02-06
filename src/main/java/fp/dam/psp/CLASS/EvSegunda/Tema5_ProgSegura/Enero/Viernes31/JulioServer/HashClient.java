package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Enero.Viernes31.JulioServer;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.security.MessageDigest;
import java.util.Base64;

public class HashClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java HashClient <algoritmo> <fichero>");
            return;
        }

        try (Socket socket = new Socket("localhost", 9000)) {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // Enviar comando "hash"
            out.writeUTF("hash");

            // Enviar algoritmo
            out.writeUTF(args[0]);

            // Leer y enviar el fichero
            FileInputStream fileIn = new FileInputStream(args[1]);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileIn.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            fileIn.close();
            socket.shutdownOutput();

            // Recibir el hash en Base64
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String hashBase64 = in.readUTF();
            System.out.println("Hash recibido: " + hashBase64);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
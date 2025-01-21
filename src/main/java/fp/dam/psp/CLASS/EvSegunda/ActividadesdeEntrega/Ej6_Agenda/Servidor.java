package fp.dam.psp.CLASS.EvSegunda.ActividadesdeEntrega.Ej6_Agenda;

import java.util.*;
import java.net.*;
import java.io.*;

public class Servidor {

    private static Map<String, Set<String>> contactos = Collections.synchronizedMap(new TreeMap<>());

    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(6000)) {
            System.out.println(". . . Servidor iniciando . . . ");

            while (true) {
                Socket clSck = s.accept();
                new Thread(new Contactos(clSck)).start();
            }

        } catch (Exception e) {
            System.out.println("> Error: " + e.getMessage());
        }
    }

    static class Contactos implements Runnable {
        private Socket sck;

        public Contactos(Socket sck) {
            this.sck = sck;
        }

        @Override
        public void run() {
            try (DataInputStream in = new DataInputStream(sck.getInputStream());
                    DataOutputStream out = new DataOutputStream(sck.getOutputStream())) {
                while (true) {
                    String pt = in.readUTF();
                    procesarPT(pt, out);
                }
            } catch (EOFException e) {
                System.out.println("> Cliente Desconectado");
            } catch (IOError e) {
                System.out.println("> Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("> Error: " + e.getMessage());

            }
        }

        private void procesarPT(String peticion, DataOutputStream out) throws IOException {
            if (peticion.equals("contactos")) {
                synchronized (contactos) {
                    out.writeUTF("OK");
                    StringBuilder sb = new StringBuilder();

                    contactos.forEach((nombre, telefonos) -> sb.append(nombre)
                            .append(String.join(", ", telefonos))
                            .append("\n"));
                    out.writeUTF(sb.toString());
                }
                return;
            }
            String[] ptr = peticion.split(":");
            if (ptr.length != 2) {
                enviarErrorSintaxis(peticion, 0, out);
                return;
            }

            String cmd = ptr[0].trim();
            String parametro = ptr[1].trim();

            switch (cmd.toLowerCase()) {
                case "nombre":
                    procesarAlta(parametro, out);
                    break;
                case "buscar":
                    procesarBusqueda(parametro, out);
                    break;
                case "eliminar":
                    procesarEliminar(parametro, out);
                    break;

                default:
                    enviarErrorSintaxis(peticion, 0, out);
            }

        }

        // TODO: AÑADIR
        private void procesarAlta(String dt, DataOutputStream out) throws IOException {
            String[] ptr = dt.trim().split(" ");
            if (ptr.length != 2) {
                enviarErrorSintaxis(dt, 0, out);
                return;
            }

            String nmb = ptr[0].trim();
            String tlf = ptr[1].trim();

            if (!esNumerico(tlf)) {
                enviarErrorSintaxis(tlf, 0, out);
                return;
            }
            synchronized (contactos) {
                Set<String> tlfs = contactos.computeIfAbsent(nmb, k -> new HashSet<>());

                if (!tlfs.add(tlf)) {
                    out.writeUTF("ERROR1");
                    return;
                }

                out.writeUTF("ok");
                out.writeUTF("");
            }

            /*
             * 
             * String[] ptr = dt.split("\s+");
             * 
             * if (ptr.length != 2 || !ptr[1].matches("\d+")) {
             * enviarErrorSintaxis(dt, 0, out);
             * return;
             * }
             * 
             * String nmb = ptr[0];
             * String tlf = ptr[1];
             * 
             * synchronized (contactos) {
             * Set<String> tlfs = contactos.computeIfAbsent(nmb, k -> new HashSet<>());
             * 
             * if (!tlfs.add(tlf)) {
             * out.writeUTF("ERROR1");
             * return;
             * }
             * out.writeUTF("OK");
             */

        }

        // TODO: BUSQUEDA
        private void procesarBusqueda(String nmb, DataOutputStream out) throws IOException {
            synchronized (contactos) {

                Set<String> tlf = contactos.get(nmb);
                if (tlf == null) {
                    out.writeUTF("ERROR2");
                    return;

                }
                out.writeUTF("OK");
                out.writeUTF(String.join("\n", tlf));
            }

        }

        // TODO: ELIMINAR
        private void procesarEliminar(String nmb, DataOutputStream out) throws IOException {
            synchronized (contactos) {
                if (contactos.remove(nmb) == null) {
                    out.writeUTF("ERROR2");
                    return;
                }
                out.writeUTF("OK");
                out.writeUTF("");
            }
        }

        // TODO: Enviar Errores
        private void enviarErrorSintaxis(String peticion, int i, DataOutputStream out) throws IOException {
            synchronized (contactos) {
                out.writeUTF("ERR03");
                out.writeUTF(peticion + "\n" + " ".repeat(i) + "^");
            }
        }

        // TODO: Comprobar numero
        private boolean esNumerico(String tlf) {
            for (char c : tlf.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }

    }

    // ! END PROGRAM
}

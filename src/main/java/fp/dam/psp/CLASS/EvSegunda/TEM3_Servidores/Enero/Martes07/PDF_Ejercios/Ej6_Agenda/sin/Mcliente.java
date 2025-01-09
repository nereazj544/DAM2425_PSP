package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.Enero.Martes07.PDF_Ejercios.Ej6_Agenda.sin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class Mcliente implements Runnable {

    private Socket socket;
    private static ConcurrentHashMap<String, Set<String>> contacto = new ConcurrentHashMap<>();

    public Mcliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);) {
            String p = bf.readLine();
            Peticion(p, pw);

        } catch (IOException e) {
            System.out.println("> ERROR EN EL CLIENTE");
            System.out.println("====================================");
            e.printStackTrace();
        }
    }

    private void Peticion(String p, PrintWriter pw) {
        if (p == null) {
            pw.println("ERROR3");
            return;
        }

        if (p.equals("contactos")) {
            mostrarContactos(pw);
            return;
        }

        String[] par = p.split(":");
        if (par.length != 2) {
            enviarError(p, 0, pw);
            return;
        }

        String cmd = par[0];
        String parame = par[1];

        switch (cmd) {
            case "buscar":
                bucarContacto(parame, pw);
                break;
            case "Eliminar":
                eliminarContacto(parame, pw);
                break;

            default:
                if (cmd.matches("[a-zA-Z]+") && parame.matches("\\d+")) {
                    agregarContacto(cmd, parame, pw);
                } else {
                    enviarError(p, 0, pw);
                }
                break;
        }

    }

    private void agregarContacto(String nombre, String telefono, PrintWriter salida) {
        contacto.computeIfAbsent(nombre, k -> new ConcurrentSkipListSet<>());
        Set<String> telefonos = contacto.get(nombre);

        if (telefonos.contains(telefono)) {
            salida.println("ERR01");
        } else {
            telefonos.add(telefono);
            salida.println("OK");
        }
    }
    private void bucarContacto(String nombre, PrintWriter salida) {
            Set<String> telefonos = contacto.get(nombre);
            if (telefonos == null) {
                salida.println("ERR02");
            } else {
                salida.println("OK");
                telefonos.forEach(salida::println);
            }
        }
        
        private void eliminarContacto(String nombre, PrintWriter salida) {
            if (contacto.remove(nombre) != null) {
                salida.println("OK");
            } else {
                salida.println("ERR02");
            }
        }
        
        private void mostrarContactos(PrintWriter salida) {
            salida.println("OK");
            new TreeSet<>(contacto.keySet()).forEach(salida::println);
        }
        
        private void enviarError(String peticion, int posicion, PrintWriter salida) {
            salida.println("ERR03");
            salida.println(peticion);
            salida.println("^".repeat(posicion) + "^");
        }
    }



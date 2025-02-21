package fp.dam.DB.Server;


import java.io.*;
import java.net.*;
import java.sql.*;
import java.sql.Statement;

public class ConexionSql implements Runnable {
    private final Socket sck;

    public ConexionSql(Socket sck) {
        this.sck = sck;
    }

    @Override
    public void run() {
        try (sck;
                Connection conexion = DriverManager.getConnection(
                        "jdbc:mysql://localhost/npi?useSSL=true&serverTimezone=UTC", "haku", "nas4");

                DataInputStream in = new DataInputStream(sck.getInputStream());
                DataOutputStream out = new DataOutputStream(sck.getOutputStream());) {
            out.writeUTF("> OPCIONES: ");
            out.writeUTF("> 'empresa' / 'personaje' ");
            String cmd = in.readUTF();

            System.out.println("> Comando recibido: " + cmd);

            Statement s = conexion.createStatement();
            ResultSet rs;
            String sql;

            switch (cmd.toLowerCase()) {
                case "empresa":
                    sql = "SELECT * FROM empresa";
                    rs = s.executeQuery(sql);
                    while (rs.next()) {
                        out.writeUTF("ID: " + rs.getInt(1));
                        out.writeUTF("Nombre Empresa: " + rs.getString(2));
                    }
                    rs.close();
                    break;

                case "personajes":
                    sql = "SELECT * FROM personajes";
                    rs = s.executeQuery(sql);
                    while (rs.next()) {
                        out.writeUTF("ID: " + rs.getInt(1));
                        out.writeUTF("Nombre: " + rs.getString(2));
                        out.writeUTF("Juego: " + rs.getString(3));
                        out.writeUTF("ID Empresa: " + rs.getInt(4));
                    }
                    rs.close();
                    break;

                default:
                    out.writeUTF("> No se ha reconocido el comando");
                    break;
            }

            // TODO Auto-generated method stub

            s.close();

        } catch (Exception e) {
            System.out.println("> Error: " + e.getMessage());
        }

    }

}

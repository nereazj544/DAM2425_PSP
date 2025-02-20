package DB.Server;

import java.io.DataInput;
import java.io.DataInputStream;
import java.net.Socket;

public class ConexionSql implements Runnable {
    private final Socket sck;

    public ConexionSql(Socket sck) {
        this.sck = sck;
    }

    @Override
    public void run() {
        try (sck) {
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/npi?useSSL=true&serverTimezone=UTC", "haku", "nas4");

            DataInputStream in = new DataInputStream(sck.getInputStream());
            DataInput out = new DataInputStream(sck.getOutputStream());

            // TODO Auto-generated method stub
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}

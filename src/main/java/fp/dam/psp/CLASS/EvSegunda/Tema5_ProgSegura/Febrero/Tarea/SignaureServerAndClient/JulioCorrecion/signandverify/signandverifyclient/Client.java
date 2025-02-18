package fp.dam.psp.CLASS.EvSegunda.Tema5_ProgSegura.Febrero.Tarea.SignaureServerAndClient.JulioCorrecion.signandverify.signandverifyclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class Client extends JFrame{
    private  String host;
    private int puerto;

    public Client(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    private Client() {
        super("Firma y verificación");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            setContentPane(new MainPanel());
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(Client::new);
    }

}

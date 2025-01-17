package fp.dam.psp.CLASS.EvSegunda.TEM3_Servidores.ActividadesdeEntrega.ClienteconSwing;

import javax.swing.*;

public class Client extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextArea txt = new JTextArea();
    private  JButton bt = new JButton("ENVIAR");


    public Client() {
        super("Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
    private void iniciar(){
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Client()::iniciar);
    }



}

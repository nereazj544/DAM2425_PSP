package fp.dam.psp.Swing;

import java.awt.*;

import javax.swing.*;

public class MainFrame extends JFrame {
    final private Font mainDFont = new Font("Gotham", Font.BOLD, 18);
    JTextField tf;
    
    public void i() {

        /* FORM PANEL */ 
        JLabel Name = new JLabel("Nose");
        Name.setFont(mainDFont);
        
        // tf = new 
        
        
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(128, 128, 255));

        setTitle("Swing");
        setSize(500, 600);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

package fp.dam.psp.CLASS.EvPrimera.ActividadesDeEntrega.Tema2.Noviembre.ACTIVIDADESDEENTREGA.Multicontador;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MultiContadorHilos extends JFrame {
    // ! Contadores == Valores
    JTextArea[] par;
    Semaphore[] valores;

    // ! Contador & Valor (GLOBAL)
    JTextArea r;
    Semaphore valoresR;

    // ! Hilos ⤵️
    ExecutorService service;

    public MultiContadorHilos() {
        super("Multicontador");

        // ! DIMESIONES
        setLayout(new GridLayout(4, 1));

        // ! TEXTO & BOTONES == (LOS TRES PRIMEROS)
        par = new JTextArea[3];
        valores = new Semaphore[3];
        // par.setEditable(false);

        // ! INICIAR 🚥
        for (int i = 0; i < 3; i++) {
            valores[i] = new Semaphore(0);
        }

        // ! TEXTO & BOTONES == (GLOBAL)
        valoresR = new Semaphore(0);
        r = new JTextArea("0");
        r.setEditable(false);

        // * POOL DE 10
        service = Executors.newFixedThreadPool(10);

        // ! == GENERAR BOTOS ===
        for (int i = 0; i < 3; i++) {
            int in = i;
            int cl = 5; // ? Columnas del Con

            // ! PANEL
            JPanel panel = new JPanel(new FlowLayout());

            // ! TEXTO AREA
            JTextArea Con = new JTextArea("0");
            Con.setColumns(cl);
            Con.setEditable(false);
            // Con.getFont();
            // Con.setFont(new Font("Arial", Font.BOLD, 120));
            Con.setBackground(new Color(155, 248, 235));

            // TODO TEXTO MODICIACION
            try {
                File fontDile = new File(
                        "src\\main\\java\\fp\\dam\\psp\\EvPrimera\\TEMA2\\Noviembre\\ACTIVIDADESDEENTREGA\\Multicontador\\res\\Calculator.ttf");
                if (!fontDile.exists()) {
                    System.err.println("> No existe maripili o no se ecuentra :v");
                }

                Font cal = Font.createFont(Font.TRUETYPE_FONT, fontDile);

                GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();

                environment.registerFont(cal);
                Font ft = cal.deriveFont(Font.BOLD, 120f);
                Con.setFont(ft);

            } catch (Exception e) {
                // TODO: handle exception
            }

            // ! BOTONES (SIN IMAGENES)
            // JButton REST = new JButton("REST");
            // JButton MAS = new JButton("+");

            // ! BOTONES (CON IMAGENES)
            JButton REST = new JButton();
            JButton MAS = new JButton();

            // ! IMAGENES
            ImageIcon img_REST = new ImageIcon(
                    "src\\main\\java\\fp\\dam\\psp\\EvPrimera\\TEMA2\\Noviembre\\ACTIVIDADESDEENTREGA\\Multicontador\\res\\rest.jpg");
            ImageIcon img_MAX = new ImageIcon(
                    "src\\main\\java\\fp\\dam\\psp\\EvPrimera\\TEMA2\\Noviembre\\ACTIVIDADESDEENTREGA\\Multicontador\\res\\plus.png");

            // ? REDIMENSION == REST
            Image imRES = img_REST.getImage();
            Image imgR_REST = imRES.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            ImageIcon ic_REST = new ImageIcon(imgR_REST);

            // ? REDIMENSION == MAS
            Image imMAZ = img_MAX.getImage();
            Image imgM_mx = imMAZ.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            ImageIcon ic_MAX = new ImageIcon(imgM_mx);

            // todo = fondo
            REST.setBackground(Color.ORANGE);
            MAS.setBackground(Color.GREEN);

            MAS.setIcon(ic_MAX);
            REST.setIcon(ic_REST);

            // ! ==== FUNCIONES DE ACCION ===

            MAS.addActionListener(e -> service.submit(() -> {
                valores[in].release();
                SwingUtilities.invokeLater(() -> {
                    Con.setText(String.valueOf(valores[in].availablePermits()));
                    actual();
                });
            }));
            REST.addActionListener(e -> service.submit(() -> {
                valores[in].release();
                SwingUtilities.invokeLater(() -> {
                    Con.setText("0");
                    actual();
                });
            }));

            par[i] = Con;
            panel.add(REST);
            panel.add(Con);
            panel.add(MAS);
            add(panel);

        }

        // TODO MODULO GLOBAL (CONTADOR DE ABAJO)

        // ! PANLE
        JPanel panG = new JPanel(new FlowLayout());

        // ! BOTON
        JButton reG = new JButton("RETS");

        // ! ACCIONES DE LOS BOTONES
        reG.addActionListener(e -> service.submit(() -> {
            for (Semaphore se : valores) {
                se.drainPermits();
            }

            valoresR.drainPermits();
            SwingUtilities.invokeLater(() -> {
                for (JTextArea jTextArea : par) {
                    jTextArea.setText("0");
                }
                r.setText("0");
            });
        }));

        // TODO FONT

        // ! INTENTO 1
        /*
         * 
         * try {
         * Font calFont = Font.createFont(Font.TRUETYPE_FONT, new File(
         * "src\\main\\java\\fp\\dam\\psp\\EvPrimera\\TEMA2\\Noviembre\\ACTIVIDADESDEENTREGA\\Multicontador\\res\\Calculator.ttf"
         * ));
         * 
         * 
         * GraphicsEnvironment environment =
         * GraphicsEnvironment.getLocalGraphicsEnvironment();
         * environment.registerFont(calFont);
         * 
         * 
         * Font fnt = calFont.deriveFont(120);
         * 
         * reG.setFont(fnt);
         * } catch (FontFormatException | IOException e1) {
         * // TODO Auto-generated catch block
         * e1.printStackTrace();
         * }
         * 
         */

        try {
            File fontDile = new File(
                    "src\\main\\java\\fp\\dam\\psp\\EvPrimera\\TEMA2\\Noviembre\\ACTIVIDADESDEENTREGA\\Multicontador\\res\\Calculator.ttf");
            if (!fontDile.exists()) {
                System.err.println("> No existe maripili o no se ecuentra :v");
            }

            Font cal = Font.createFont(Font.TRUETYPE_FONT, fontDile);

            GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();

            environment.registerFont(cal);
            Font ft = cal.deriveFont(Font.BOLD, 120f);
            r.setFont(ft);

        } catch (Exception e) {
            // TODO: handle exception
        }

        // TODO IMAGENES

        // ! IMAGENES (COPIA Y PEGA DEL REST 👍)
        ImageIcon img_REST = new ImageIcon(
                "src\\main\\java\\fp\\dam\\psp\\EvPrimera\\TEMA2\\Noviembre\\ACTIVIDADESDEENTREGA\\Multicontador\\res\\rest.jpg");

        // ? REDIMENSION == REST
        Image imRES = img_REST.getImage();
        Image imgR_REST = imRES.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon ic_REST = new ImageIcon(imgR_REST);

        // todo = fondo
        reG.setBackground(Color.ORANGE);
        reG.setIcon(ic_REST);

        // r.setFont(new Font("Arial", Font.BOLD, 120));
        r.setBackground(new Color(197, 200, 0));

        // TODO PANEL GL

        // ! AÑADIR AL PANEL GL
        panG.add(reG);
        panG.add(r);
        add(panG);

        // ? MOVIDAS DEL SWING
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    // ! == ACTUALIZAR ==
    private void actual() {

        service.submit(() -> {
            int s = 0;
            for (Semaphore sum : valores) {
                s += sum.availablePermits();
            }
            final int re = s;
            SwingUtilities.invokeLater(() -> r.setText(
                    String.valueOf(re)));
        });

        // ! DE UNA FOMRA QUE NO CARRULA :)
        /*
         * service.submit(new Runnable() {
         * 
         * @Override
         * public void run() {
         * int sum = 0;
         * for (Semaphore pr : valores) {
         * sum += pr.availablePermits();
         * }
         * 
         * valoresR.drainPermits();
         * 
         * for (int i = 0; i < sum; i++) {
         * valoresR.drainPermits();
         * }
         * 
         * SwingUtilities.invokeLater(() -> r.setText(String.valueOf(sum)));
         * }
         * 
         * });
         */
    }

    // ! MAIN
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MultiContadorHilos::new);
    }
}

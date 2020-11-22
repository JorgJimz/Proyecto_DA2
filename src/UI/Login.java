package UI;

import COMMON.MyCustomButton;
import DAO.UsuarioController;
import MODEL.Usuario;
import UTIL.Util;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Login extends JFrame {

    private JLabel imgLogo;
    private JButton btnLogin;
    private JButton btnCerrar;
    private JLabel jLabel2;
    private JLabel jLabel1;
    private JPasswordField txtPassword;
    private JTextField txtUsuario;
    private JPanel pnlLogin;
    UsuarioController controller = new UsuarioController();

    public Login() {

        pnlLogin = new JPanel() {
            protected int strokeSize = 1;
            protected Color shadowColor = Color.BLACK;
            protected boolean shady = true;
            protected boolean highQuality = true;
            protected Dimension arcs = new Dimension(20, 20);
            protected int shadowGap = 5;
            protected int shadowOffset = 4;
            protected int shadowAlpha = 150;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int width = getWidth();
                int height = getHeight();
                int shadowGap = this.shadowGap;
                Color shadowColorA = new Color(shadowColor.getRed(),
                        shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
                Graphics2D graphics = (Graphics2D) g;

                if (highQuality) {
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                }

                if (shady) {
                    graphics.setColor(shadowColorA);
                    graphics.fillRoundRect(
                            shadowOffset,
                            shadowOffset,
                            width - strokeSize - shadowOffset,
                            height - strokeSize - shadowOffset,
                            arcs.width, arcs.height);
                } else {
                    shadowGap = 1;
                }

                graphics.setColor(new Color(228, 1, 55));
                graphics.fillRoundRect(0, 0, width - shadowGap,
                        height - shadowGap, arcs.width, arcs.height);
                graphics.setColor(new Color(228, 1, 55));
                graphics.setStroke(new BasicStroke(strokeSize));
                graphics.drawRoundRect(0, 0, width - shadowGap,
                        height - shadowGap, arcs.width, arcs.height);
                graphics.setStroke(new BasicStroke());
            }
        };

        pnlLogin.setBounds(0, 117, 426, 250);
        pnlLogin.setOpaque(false);
        pnlLogin.setLayout(null);
        getContentPane().add(pnlLogin, BorderLayout.CENTER);

        imgLogo = new JLabel(new ImageIcon("img/logo.png"));
        getContentPane().add(imgLogo, BorderLayout.WEST);
        imgLogo.setBounds(23, 14, 375, 121);

        txtUsuario = new JTextField();
        pnlLogin.add(txtUsuario);
        txtUsuario.setBounds(138, 40, 260, 25);
        txtUsuario.setOpaque(false);
        txtUsuario.setFont(new Font("Century Gothic", Font.BOLD, 18));
        txtUsuario.setForeground(Color.WHITE);
        txtUsuario.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));

        jLabel1 = new JLabel();
        pnlLogin.add(jLabel1);
        jLabel1.setText("USUARIO");
        jLabel1.setFont(new Font("Century Gothic", Font.BOLD, 18));
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setBounds(23, 40, 103, 25);

        txtPassword = new JPasswordField();
        pnlLogin.add(txtPassword);
        txtPassword.setBounds(138, 100, 260, 25);
        txtPassword.setOpaque(false);
        txtPassword.setFont(new Font("Century Gothic", Font.BOLD, 18));
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        txtPassword.addKeyListener(new KeyAdapter() {            
            @Override
            public void keyReleased(KeyEvent ke) {
                if(ke.getKeyCode() == KeyEvent.VK_ENTER){
                    IniciarSesion();
                }
            }
        });

        jLabel2 = new JLabel();
        pnlLogin.add(jLabel2);
        jLabel2.setText("PASSWORD");
        jLabel2.setFont(new Font("Century Gothic", Font.BOLD, 18));
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setBounds(23, 100, 103, 25);

        btnLogin = new MyCustomButton("img/login.png", "ENTRAR", true);
        btnLogin.setBounds(128, 160, 70, 70);
        pnlLogin.add(btnLogin);
        btnLogin.addActionListener((ActionEvent ae) -> {
            IniciarSesion();
        });

        btnCerrar = new MyCustomButton("img/cerrar.png", "SALIR", true);
        pnlLogin.add(btnCerrar);
        btnCerrar.setBounds(208, 160, 70, 70);
        btnCerrar.addActionListener((ActionEvent ae) -> {
            Salir();
        });

        this.setLayout(null);
        this.setSize(426, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setVisible(true);
        this.setBackground(new Color(0, 0, 0, 0));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    public void Salir() {
        this.dispose();
    }

    public void IniciarSesion() {
        Usuario user = controller.IniciarSesion(txtUsuario.getText(), txtPassword.getText());
        if (user != null) {
            try {
                UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaMauveMetallicLookAndFeel");
                new Principal(user);
                Salir();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            Util.Mensaje("¡Usuario y/o Contraseña incorrectos!", "Ingreso no Autorizado", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            new Login();
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

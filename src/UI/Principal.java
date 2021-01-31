package UI;

import DAO.UsuarioController;
import MODEL.Aplicacion;
import MODEL.Usuario;
import UTIL.Util;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import static java.util.Comparator.comparingInt;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;
import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class Principal extends JFrame {

    public JMenuBar menuBarPrincipal;

    public static JDesktopPane contenedor;
    public static Usuario USUARIO;
    UsuarioController controller = new UsuarioController();

    public Principal(Usuario usr) throws HeadlessException {
        USUARIO = usr;

        ArrayList<Aplicacion> arrApp = controller.ObtenerMenu(String.valueOf(USUARIO.getPERFIL().getID()));

        Util.Mensaje("¡Hola! " + usr.getPERSONA(), "Bienvenido", JOptionPane.INFORMATION_MESSAGE);

        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle(".:: SISTEMA DE ORDENES DE COMPRA - LAS DELICIAS ::.");

        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(this.getWidth(), 20));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        JLabel statusLabel = new JLabel("USUARIO ACTUAL: " + usr.getPERSONA() + " [" + usr.getPERFIL().getDESCRIPCION() + "]");
        statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        statusLabel.setFont(new Font("Century Gothic", Font.BOLD, 14));
        statusPanel.add(statusLabel);

        contenedor = new JDesktopPane();

        menuBarPrincipal = new JMenuBar();

        this.setJMenuBar(menuBarPrincipal);

        ArrayList<Aplicacion> modulos;
        modulos = arrApp.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(Aplicacion::getMODULO_ID))),
                ArrayList::new));

        for (Aplicacion modulo : modulos) {
            JMenu mn = new JMenu(modulo.getMODULO().getDESCRIPCION());
            mn.setFont(new Font("Century Gothic", Font.BOLD, 14));
            arrApp.forEach((app) -> {
                if (app.getMODULO_ID() == modulo.getMODULO_ID()) {
                    JMenuItem mni = new JMenuItem(app.getDESCRIPCION());
                    mni.setFont(new Font("Century Gothic", Font.BOLD, 14));
                    mni.addActionListener((ActionEvent ae) -> {
                        JInternalFrame iFrame;
                        try {                            
                            iFrame = (JInternalFrame) Class.forName(app.getCLASE()).newInstance();
                            Principal.contenedor.add(iFrame);
                            iFrame.moveToFront();
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    mn.add(mni);
                }
            });
            menuBarPrincipal.add(mn);
        }

        getContentPane().add(contenedor, BorderLayout.CENTER);
    }

}

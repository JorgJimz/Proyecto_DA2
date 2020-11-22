package UI;

import MODEL.Usuario;
import UTIL.Util;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
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

    public JMenu menuAlmacen;
    public JMenu menuCompras;

    public JMenuItem genOrden;
    public JMenuItem recOrden;
    public JMenuItem proOrden;

    public JDesktopPane contenedor;
    public static Usuario USUARIO;

    public Principal(Usuario usr) throws HeadlessException {
        USUARIO = usr;

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
        JLabel statusLabel = new JLabel("USUARIO ACTUAL: " + usr.getPERSONA() + " [" + usr.getPERFIL() + "]");
        statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        statusLabel.setFont(new Font("Century Gothic", Font.BOLD, 14));
        statusPanel.add(statusLabel);

        contenedor = new JDesktopPane();

        menuBarPrincipal = new JMenuBar();
        menuAlmacen = new JMenu("Almacen");
        menuCompras = new JMenu("Compras");
        menuAlmacen.setFont(new Font("Century Gothic", Font.BOLD, 14));
        menuCompras.setFont(new Font("Century Gothic", Font.BOLD, 14));

        genOrden = new JMenuItem("Generar Orden");
        genOrden.setFont(new Font("Century Gothic", Font.BOLD, 14));
        menuAlmacen.add(genOrden);
        genOrden.addActionListener((ActionEvent ae) -> {
            Generar_Orden ui_gen_ped = new Generar_Orden();
            contenedor.add(ui_gen_ped);
        });

        recOrden = new JMenuItem("Recibir Orden");
        recOrden.setFont(new Font("Century Gothic", Font.BOLD, 14));
        menuAlmacen.add(recOrden);
        recOrden.addActionListener((ActionEvent ae) -> {
            Recibir_Orden ui_rec_ped = new Recibir_Orden();
            contenedor.add(ui_rec_ped);
        });

        proOrden = new JMenuItem("Procesar Orden");
        proOrden.setFont(new Font("Century Gothic", Font.BOLD, 14));
        menuCompras.add(proOrden);
        proOrden.addActionListener((ActionEvent ae) -> {
            Procesar_Orden ui_pro_ped = new Procesar_Orden();
            contenedor.add(ui_pro_ped);
        });

        menuBarPrincipal.add(menuCompras);
        menuBarPrincipal.add(menuAlmacen);

        this.setJMenuBar(menuBarPrincipal);

        getContentPane().add(contenedor, BorderLayout.CENTER);
    }

}

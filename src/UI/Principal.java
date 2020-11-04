/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author usuario
 */
public class Principal extends JFrame{

    /**
     * @param args the command line arguments
     */
    
    public JMenuBar menuBarPrincipal;
    
    public JMenu menuAlmacen;
    public JMenu menuCompras;
    
    public JMenuItem genPedido;
    public JMenuItem recPedido;
    
    public JDesktopPane contenedor;
    
    public Principal() throws HeadlessException {
        this.setVisible(true);
        this.setSize(100,100);
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        contenedor = new JDesktopPane();
        
        getContentPane().add(contenedor, BorderLayout.CENTER);
        
        menuBarPrincipal = new JMenuBar();
        menuAlmacen = new JMenu("Almacen");
        menuCompras = new JMenu("Compras");
        
        genPedido = new JMenuItem("Generar Pedido");
        menuAlmacen.add(genPedido);
        genPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               Generar_Pedido ui_gen_ped = new Generar_Pedido();
               contenedor.add(ui_gen_ped);
            }
        });
        
        recPedido = new JMenuItem("Recibir Pedido");
        menuAlmacen.add(recPedido);
        recPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Recibir_Pedido ui_rec_ped = new Recibir_Pedido();
                contenedor.add(ui_rec_ped);
            }
        });
        
        menuBarPrincipal.add(menuCompras);
        menuBarPrincipal.add(menuAlmacen);
        
        this.setJMenuBar(menuBarPrincipal);
    }

    public static void main(String[] args) {
        // TODO code application logic here
        new Principal();
    }
    
}

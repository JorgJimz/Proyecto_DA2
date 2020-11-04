package UI;

import UTIL.dbBean;
import java.sql.ResultSet;
import javax.swing.JInternalFrame;
import javax.swing.*;

public class Generar_Pedido extends JInternalFrame {

    private JScrollPane spTabla;
    private JTable tbProducto;
    private JLabel jLabel1;
    private JTextField txtBuscador;

    public Generar_Pedido() {
        this.setVisible(true);
        this.setSize(1500, 1500);
        this.setClosable(true);
        this.setTitle("Generar Pedido");

        spTabla = new JScrollPane();
        getContentPane().add(spTabla);
        spTabla.setBounds(12, 75, 529, 162);
        tbProducto = new JTable();
        spTabla.setViewportView(tbProducto);
        txtBuscador = new JTextField();
        getContentPane().add(txtBuscador);
        txtBuscador.setBounds(12, 40, 527, 23);
        jLabel1 = new JLabel();
        getContentPane().add(jLabel1);
        jLabel1.setText("Producto");
        jLabel1.setBounds(12, 12, 49, 16);
        
        CargarProductos();

    }

    public void CargarProductos() {
        try {
            dbBean cnn = new dbBean();
            
            
            ResultSet response = cnn.execSQL("SELECT * FROM PRODUCTO");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

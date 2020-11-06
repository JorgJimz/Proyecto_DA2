package UI;

import MODEL.Producto;
import UTIL.dbBean;
import java.sql.ResultSet;
import javax.swing.JInternalFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Generar_Pedido extends JInternalFrame {
    
 
// la primer tabla
    private JScrollPane spTabla;
    private JTable tbProducto;
    private JLabel jLabel1;
    private JTextField txtBuscador;
    DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"ID","DESCRIPCION","PRECIO","UNIDAD DE MEDIDA","STOCK ACTUAL"});

    // 2da tabla
    private JScrollPane spProducto;
    private JTable tbProducto2;
    
    DefaultTableModel modelo2 = new DefaultTableModel(null, new String[]{"ID","DESCRIPCION","PRECIO","CANTIDAD","IMPORTE"});
    //
    
    public Generar_Pedido() {
        this.setVisible(true);
        this.setSize(1500, 1500);
        this.setClosable(true);
        this.setTitle("Generar Pedido");
        
        //
         this.setVisible(true);
        this.setSize(1500, 1500);
        this.setLayout(null);
        this.setClosable(true);
        this.setTitle("Detalle Pedido");
        
          tbDetalle = new JTable();
        getContentPane().add(tbDetalle);
        //
        
        spTabla = new JScrollPane();
        getContentPane().add(spTabla);
        spTabla.setBounds(12, 75, 529, 162);
        
        tbProducto = new JTable();
        spTabla.setViewportView(tbProducto);
        
        tbProducto.setModel(modelo);
        txtBuscador = new JTextField(); // buscador
        getContentPane().add(txtBuscador);
        txtBuscador.setBounds(12, 40, 527, 23);
        jLabel1 = new JLabel(); // etiqueta
        getContentPane().add(jLabel1);
        jLabel1.setText("Producto"); // etiqueta
        jLabel1.setBounds(12, 12, 49, 16);
        
        // 2da tabla
        spProducto = new JScrollPane();
        getContentPane().add(spProducto);
        spProducto.setBounds(771, 56, 657, 371);
        
        
        tbProducto2 = new JTable();
        spProducto.setViewportView(tbProducto2);
        tbProducto2.setModel(modelo2);      
        //
        

        CargarProductos();
              
        
    }
   
    

    public void CargarProductos() {
        try {
            dbBean cnn = new dbBean();

            ResultSet rs = cnn.execSQL("SELECT * FROM PRODUCTO");
            
            while(rs.next()){              
                
                modelo.addRow(new Object[]{rs.getInt("ID"), rs.getString("DESCRIPCION"),rs.getDouble("PRECIO"),rs.getString("UNIDAD_MEDIDA"),rs.getInt("STOCK_ACTUAL")});
   
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
      // 2da tabla detalle
    private JTable tbDetalle;
     // fin
    
    
         
        /// 2da tabla detalle pedido
                    /*
        this.setVisible(true);
        this.setSize(1500, 1500);
        this.setLayout(null);
        this.setClosable(true);
        this.setTitle("Detalle Pedido");
        
        tbDetalle = new JTable();
        getContentPane().add(tbDetalle);
        
        spTabla = new JScrollPane();
        getContentPane().add(spTabla);
        spTabla.setBounds(214, 48, 923, 583);
        
        tbDetalle = new JTable();
        spTabla.setViewportView(tbDetalle);
        tbDetalle.setModel(modelo);

    
    // fin 2da tabla */
}

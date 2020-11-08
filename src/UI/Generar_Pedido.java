package UI;

import MODEL.Producto;
import UTIL.dbBean;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import javax.swing.JInternalFrame;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class Generar_Pedido extends JInternalFrame {

// la primer tabla
    private JScrollPane spTabla;
    private JTable tbProducto;
    private JLabel jLabel1;
    private JTextField txtBuscador;
    DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"ID", "DESCRIPCION", "PRECIO", "UNIDAD DE MEDIDA", "STOCK ACTUAL"}) {
        public boolean isCellEditable(int rowIndex, int colIndex) {
            return false;
        }
    };

    // 2da tabla
    private JScrollPane spProducto;
    private JTable tbProducto2;

    DefaultTableModel modelo2 = new DefaultTableModel(null, new String[]{"ID", "DESCRIPCION", "PRECIO", "CANTIDAD", "IMPORTE"}) {
        public boolean isCellEditable(int rowIndex, int colIndex) {
            if (colIndex == 3) {
                return true;
            }
            return false;
        }
    };
    //

    //
    private JButton btGenerar;
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
        tbProducto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    int fila = tbProducto.getSelectedRow();

                    int id = Integer.parseInt(tbProducto.getValueAt(fila, 0).toString());
                    String nombre = tbProducto.getValueAt(fila, 1).toString();
                    double precio = Double.parseDouble(tbProducto.getValueAt(fila, 2).toString());

                    modelo2.addRow(new Object[]{id, nombre, precio});
                }
            }
        });

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
        tbProducto2.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tme) {
                if (tme.getType() == TableModelEvent.UPDATE && tme.getColumn() == 3) {
                    int fila = tbProducto2.getSelectedRow();
                    int cantidad = Integer.parseInt(tbProducto2.getValueAt(fila, 3).toString());
                    double precio = Double.parseDouble(tbProducto2.getValueAt(fila, 2).toString());
                    tbProducto2.getModel().setValueAt(cantidad * precio, fila, 4);
                }
            }
        });

        //
        //
        btGenerar = new JButton();
        getContentPane().add(btGenerar);
        btGenerar.setText("Generar");
        btGenerar.setBounds(1151, 537, 141, 55);
        //

        CargarProductos();

    }

    public void CargarProductos() {
        try {
            dbBean cnn = new dbBean();

            ResultSet rs = cnn.execSQL("SELECT * FROM PRODUCTO");

            while (rs.next()) {

                modelo.addRow(new Object[]{rs.getInt("ID"), rs.getString("DESCRIPCION"), rs.getDouble("PRECIO"), rs.getString("UNIDAD_MEDIDA"), rs.getInt("STOCK_ACTUAL")});

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

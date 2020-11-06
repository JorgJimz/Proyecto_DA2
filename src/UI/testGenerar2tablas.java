
package UI;
import MODEL.Producto;
import UTIL.dbBean;
import java.sql.ResultSet;
import javax.swing.JInternalFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class  testGenerar2tablas extends JInternalFrame {

    private JScrollPane spTabla;
    private JTable tbProducto;
    private JLabel jLabel1;
    private JTextField txtBuscador;
    DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"ID","DESCRIPCION","PRECIO","UNIDAD DE MEDIDA","STOCK ACTUAL"});

    
}

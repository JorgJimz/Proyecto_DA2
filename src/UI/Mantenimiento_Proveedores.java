package UI;

import COMMON.MyCustomButton;
import COMMON.ProveedorTableEditor;
import DAO.ProveedorController;
import MODEL.Distrito;
import MODEL.Proveedor;
import UTIL.Util;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXTable;

public class Mantenimiento_Proveedores extends JInternalFrame {

    ProveedorController controller = new ProveedorController();
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    DefaultTableModel modelo = new DefaultTableModel(null, new Object[]{"ID", "RAZON SOCIAL", "DIRECCION", "TELEFONO", "EMAIL", "RUC", "DISTRITO", "DIAS PAGO", "CUENTA BCP", "CONTACTO"}) {
        @Override
        public boolean isCellEditable(int rowIndex, int colIndex) {
            return !(colIndex == 0);
        }
    };

    public void Limpiar() {
        Util.Limpiar(this);
    }

    public Mantenimiento_Proveedores() {

        this.setLayout(null);
        this.setVisible(true);
        this.setClosable(true);
        this.setPreferredSize(new java.awt.Dimension(1370, 700));

        jLabel1 = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        cboDistrito = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        txtDiaPago = new JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCuentaBcp = new JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtContacto = new javax.swing.JTextField();
        btnGrabar = new MyCustomButton("img/grabar.png", "GRABAR", false);
        spLibros = new javax.swing.JScrollPane();
        tbProveedor = new JXTable();
        tbProveedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbProveedor.setModel(modelo);

        btnEliminar = new MyCustomButton("img/delete.png", "ELIMINAR", false);
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setText("RAZON SOCIAL");
        jLabel1.setFont(new Font("Arial", Font.BOLD, 12));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(11, 74, 109, 28);

        txtRazonSocial.setName("txtRazonSocial"); // NOI18N
        getContentPane().add(txtRazonSocial);
        txtRazonSocial.setBounds(146, 74, 174, 28);

        txtDireccion.setName("txtDireccion"); // NOI18N
        getContentPane().add(txtDireccion);
        txtDireccion.setBounds(146, 120, 174, 28);

        txtTelefono.setName("txtTelefono"); // NOI18N
        getContentPane().add(txtTelefono);
        txtTelefono.setBounds(146, 166, 174, 28);

        txtEmail.setName("txtTelefono"); // NOI18N
        getContentPane().add(txtEmail);
        txtEmail.setBounds(146, 211, 174, 28);

        jLabel2.setText("DIRECCION");
        jLabel2.setFont(new Font("Arial", Font.BOLD, 12));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(12, 119, 109, 28);

        jLabel3.setText("TELEFONO");
        jLabel3.setFont(new Font("Arial", Font.BOLD, 12));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 165, 109, 28);

        jLabel4.setText("E-MAIL");
        jLabel4.setFont(new Font("Arial", Font.BOLD, 12));
        getContentPane().add(jLabel4);
        jLabel4.setBounds(10, 210, 109, 28);

        jLabel5.setText("RUC");
        jLabel5.setFont(new Font("Arial", Font.BOLD, 12));
        getContentPane().add(jLabel5);
        jLabel5.setBounds(10, 256, 109, 28);

        txtRuc.setName("txtRuc"); // NOI18N
        getContentPane().add(txtRuc);
        txtRuc.setBounds(146, 256, 174, 28);

        cboDistrito.setName("cboDistrito"); // NOI18N
        getContentPane().add(cboDistrito);
        cboDistrito.setBounds(146, 302, 174, 28);

        jLabel6.setText("DISTRITO");
        jLabel6.setFont(new Font("Arial", Font.BOLD, 12));
        getContentPane().add(jLabel6);
        jLabel6.setBounds(10, 301, 109, 28);

        txtDiaPago.setName("txtDiaPago"); // NOI18N
        getContentPane().add(txtDiaPago);
        txtDiaPago.setBounds(146, 348, 174, 28);

        jLabel7.setText("DIAS PAGO");
        jLabel7.setFont(new Font("Arial", Font.BOLD, 12));
        getContentPane().add(jLabel7);
        jLabel7.setBounds(10, 347, 109, 28);

        jLabel8.setText("CUENTA BCP");
        jLabel8.setFont(new Font("Arial", Font.BOLD, 12));
        getContentPane().add(jLabel8);
        jLabel8.setBounds(10, 392, 109, 28);

        txtCuentaBcp.setName("txtCuentaBcp"); // NOI18N
        getContentPane().add(txtCuentaBcp);
        txtCuentaBcp.setBounds(146, 393, 174, 28);

        jLabel9.setText("CONTACTO");
        jLabel9.setFont(new Font("Arial", Font.BOLD, 12));
        getContentPane().add(jLabel9);
        jLabel9.setBounds(10, 438, 109, 28);

        txtContacto.setName("txtEstado_P"); // NOI18N
        getContentPane().add(txtContacto);
        txtContacto.setBounds(146, 439, 174, 28);

        btnGrabar.addActionListener((ActionEvent evt) -> {
            btnGrabarActionPerformed(evt);
        });
        getContentPane().add(btnGrabar);
        btnGrabar.setBounds(115, 491, 100, 128);
        btnGrabar.putClientProperty("Synthetica.opaque", Boolean.FALSE);
        btnGrabar.setForeground(Color.BLACK);
        btnGrabar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGrabar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnGrabar.setHorizontalTextPosition(SwingConstants.CENTER);
        btnGrabar.setText("GRABAR");

        spLibros.setViewportView(tbProveedor);

        getContentPane().add(spLibros);
        spLibros.setBounds(347, 74, 1007, 512);

        getContentPane().add(btnEliminar);
        btnEliminar.setBounds(220, 491, 100, 128);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminar.setForeground(Color.BLACK);
        btnEliminar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
        btnEliminar.setText("ELIMINAR");
        btnEliminar.putClientProperty("Synthetica.opaque", Boolean.FALSE);
        btnEliminar.addActionListener((ActionEvent ae) -> {
            int option = JOptionPane.showConfirmDialog(null, "<html><h4><b>¿Seguro de eliminar este registro?</b></h4></html>", "Mensaje de Alerta",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                int fila = tbProveedor.getSelectedRow();
                controller.EliminarProveedor(Integer.parseInt(tbProveedor.getModel().getValueAt(fila, 0).toString()));
                controller.ListarProveedor(tbProveedor, modelo, "");
            }

        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("MANTENIMIENTO DE PROVEEDORES");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(10, 20, 629, 22);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(10, 50, 995, 10);

        txtBuscar = new JXSearchField("Buscar por RAZON SOCIAL");
        getContentPane().add(txtBuscar);
        txtBuscar.setBounds(1005, 35, 350, 28);
        txtBuscar.setName("txtBuscar");
        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                controller.ListarProveedor(tbProveedor, modelo, txtBuscar.getText());
            }
        });
        tbProveedor.setDefaultEditor(Object.class, new ProveedorTableEditor());
        tbProveedor.setRowHeight(25);

        modelo.addTableModelListener((TableModelEvent tme) -> {
            if (tme.getType() == TableModelEvent.UPDATE) {
                try {
                    int fila = tbProveedor.getSelectedRow();
                    Proveedor objProveedor = new Proveedor();
                    objProveedor.setID(Integer.parseInt(modelo.getValueAt(fila, 0).toString()));
                    objProveedor.setRAZON_SOCIAL(modelo.getValueAt(fila, 1).toString());
                    objProveedor.setDIRECCION(modelo.getValueAt(fila, 2).toString());
                    objProveedor.setTELEFONO(modelo.getValueAt(fila, 3).toString());
                    objProveedor.setEMAIL(modelo.getValueAt(fila, 4).toString());
                    objProveedor.setRUC(modelo.getValueAt(fila, 5).toString());
                    Distrito d = controller.ListarDistritos().stream().filter(c -> c.getNOMBRE().equalsIgnoreCase(modelo.getValueAt(fila, 6).toString())).findFirst().get();
                    objProveedor.setDISTRITO_ID(d.getID());                    
                    objProveedor.setDIAS_PAGO(Integer.parseInt(modelo.getValueAt(fila, 7).toString()));
                    objProveedor.setCTA_BCP(modelo.getValueAt(fila, 8).toString());
                    objProveedor.setCONTACTO(modelo.getValueAt(fila, 9).toString());
                    controller.ActualizarProveedor(objProveedor);
                    controller.ListarProveedor(tbProveedor, modelo, "");

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        pack();
        ListarDistritos();
        controller.ListarProveedor(tbProveedor, modelo, "");

    }

    public void ListarDistritos() {
        for (Distrito d : controller.ListarDistritos()) {
            cboDistrito.addItem(d);
        }
    }

    public void Bloquear(boolean value) {
        Util.Bloquear(this, value);
    }

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {
        if (Util.Validar(this)) {
            Proveedor p = new Proveedor();
            p.setRAZON_SOCIAL(txtRazonSocial.getText());
            p.setDIRECCION(txtDireccion.getText());
            p.setTELEFONO(txtTelefono.getText());
            p.setEMAIL(txtEmail.getText());
            p.setRUC(txtRuc.getText());
            p.setDISTRITO(((Distrito) cboDistrito.getSelectedItem()));
            p.setDIAS_PAGO(Integer.parseInt(txtDiaPago.getText()));
            p.setCTA_BCP(txtCuentaBcp.getText());
            p.setCONTACTO(txtContacto.getText());
            controller.GrabarProveedor(p);
            Limpiar();
            controller.ListarProveedor(tbProveedor, modelo, "");
        } else {
            Util.Mensaje("Completar el formulario, por favor.", "Faltan datos", JOptionPane.WARNING_MESSAGE);
        }

    }

    private javax.swing.JButton btnGrabar;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtRazonSocial;
    private JTextField txtDiaPago;
    private JTextField txtCuentaBcp;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private JTextField txtBuscar;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane spLibros;
    private javax.swing.JSeparator jSeparator1;
    private JXTable tbProveedor;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtContacto;
    private javax.swing.JComboBox cboDistrito;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtEmail;
}

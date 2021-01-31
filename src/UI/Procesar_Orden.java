package UI;

import COMMON.ButtonColumn;
import COMMON.DetalleOrdenRenderer;
import COMMON.EstadoLabel;
import COMMON.MyCustomButton;
import DAO.PedidoController;
import MODEL.Detalle_Orden;
import MODEL.Estado;
import MODEL.Orden;
import MODEL.Proveedor;
import UTIL.Util;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.JXDatePicker;

import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class Procesar_Orden extends JInternalFrame {

    private JInternalFrame container;
    private JTextField txtBuscador;
    private JTextField srcBuscador;
    private JComboBox cboProveedor;
    private JXDatePicker dpFechaEntrega;
    private JLabel jLabel8;
    private JTable tbOrdenes;
    private JLabel jLabel7;
    private JScrollPane spOrdenes;
    private JTextArea txtObs;
    private EstadoLabel lblStatus;
    private JLabel lblFecha;
    private JLabel lblOrden;
    private JLabel jLabel6;
    private JLabel jLabel5;
    private JLabel jLabel4;
    private JButton btnProcesar;
    private JButton btnAnular;
    private JScrollPane spObs;
    private JLabel jLabel3;
    private JLabel jLabel2;
    private JScrollPane scpPedidos;
    private JLabel jLabel1;
    private JTable tbDetalle;
    private Orden oOrden;
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

    PedidoController controller = new PedidoController();

    DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"ID", "DESCRIPCION", "PRECIO", "CANTIDAD", "IMPORTE"}) {
        @Override
        public boolean isCellEditable(int rowIndex, int colIndex) {
            return false;
        }
    };

    DefaultTableModel modeloOrden = new DefaultTableModel(null, new String[]{"ID", "FECHA", "USUARIO", "ESTADO", ""}) {
        @Override
        public boolean isCellEditable(int rowIndex, int colIndex) {
            if (colIndex == 4) {
                return true;
            }
            return false;
        }
    };

    public Procesar_Orden() {
        this.setLayout(null);
        this.setVisible(true);
        this.setClosable(true);
        this.setTitle(".:: PROCESAR ORDEN ::.");
        this.setSize(new java.awt.Dimension(1360, 650));
        this.setPreferredSize(new java.awt.Dimension(1360, 677));
        this.setBounds(0, 0, 1360, 677);

        srcBuscador = new JXSearchField();
        getContentPane().add(srcBuscador);
        srcBuscador.setBounds(210, 21, 442, 23);
        srcBuscador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    ListarOrdenes();
                }
            }
        });

        jLabel1 = new JLabel();
        getContentPane().add(jLabel1);
        jLabel1.setText("BUSCAR POR ORDEN");
        jLabel1.setFont(new Font("Century Gothic", Font.BOLD, 18));
        jLabel1.setForeground(Color.BLACK);
        jLabel1.setBounds(12, 24, 198, 16);

        tbDetalle = new JTable(modelo);
        tbDetalle.setRowHeight(28);
        scpPedidos = new JScrollPane();
        getContentPane().add(scpPedidos);
        scpPedidos.setViewportView(tbDetalle);
        scpPedidos.setBounds(684, 107, 640, 227);

        jLabel2 = new JLabel();
        getContentPane().add(jLabel2);
        jLabel2.setText("PROVEEDOR");
        jLabel2.setBounds(683, 356, 97, 16);
        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 12));

        cboProveedor = new JComboBox<>();
        controller.ObtenerProveedores().forEach(ci -> cboProveedor.addItem(ci));
        AutoCompleteDecorator.decorate(cboProveedor);
        getContentPane().add(cboProveedor);
        cboProveedor.setBounds(780, 353, 543, 28);

        lblStatus = new EstadoLabel();
        getContentPane().add(lblStatus);
        lblStatus.setBounds(1176, 64, 147, 15);

        jLabel3 = new JLabel();
        getContentPane().add(jLabel3);
        jLabel3.setText("OBSERVACION");
        jLabel3.setBounds(683, 439, 97, 16);
        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12));

        spObs = new JScrollPane();
        getContentPane().add(spObs);
        spObs.setBounds(780, 437, 546, 112);

        txtObs = new JTextArea();
        txtObs.setFont(new Font("Century Gothic", Font.BOLD, 14));
        spObs.setViewportView(txtObs);

        btnProcesar = new MyCustomButton("img/grabar.png", "GRABAR", false);
        btnProcesar.setEnabled(false);
        getContentPane().add(btnProcesar);
        btnProcesar.setBounds(1179, 561, 70, 70);
        btnProcesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (Util.Validar(container)) {
                    Orden o = new Orden();
                    o.setFECHA_ENTREGA(formatoFecha.format(dpFechaEntrega.getDate()));
                    o.setID(Integer.parseInt(lblOrden.getText()));
                    o.setPROVEEDOR((Proveedor) cboProveedor.getSelectedItem());
                    o.setOBS(txtObs.getText());
                    o.setFACTURA("");
                    o.setESTADO(new Estado(Estado.PROCESADA));
                    controller.ProcesarOrden(o);
                    oOrden = controller.ObtenerOrden(String.valueOf(o.getID()));
                    ImprimirOrden();
                    Cerrar();
                } else {
                    Util.Mensaje("Faltan datos, complete el formulario, por favor.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnAnular = new MyCustomButton("img/cerrar.png", "ANULAR", false);
        btnAnular.setEnabled(false);
        getContentPane().add(btnAnular);
        btnAnular.setBounds(1254, 561, 70, 70);
        btnAnular.addActionListener((ActionEvent ae) -> {
            int option = JOptionPane.showConfirmDialog(null, "<html><h4><b>¿Seguro de anular la orden?</b></h4></html>", "Mensaje de Alerta",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                Orden o = new Orden();
                o.setID(Integer.parseInt(lblOrden.getText()));
                Proveedor p = new Proveedor();
                p.setID(((Proveedor) cboProveedor.getSelectedItem()).getID());
                o.setPROVEEDOR(p);
                o.setOBS(txtObs.getText());
                o.setFACTURA("");
                o.setESTADO(new Estado(Estado.ANULADA));
                controller.ProcesarOrden(o);
                Cerrar();
            }
        });

        jLabel4 = new JLabel();
        getContentPane().add(jLabel4);
        jLabel4.setText("ORDEN:");
        jLabel4.setBounds(683, 63, 56, 16);
        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 12));

        jLabel5 = new JLabel();
        getContentPane().add(jLabel5);
        jLabel5.setText("FECHA:");
        jLabel5.setBounds(906, 63, 57, 16);
        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 12));

        jLabel6 = new JLabel();
        getContentPane().add(jLabel6);
        jLabel6.setText("STATUS");
        jLabel6.setBounds(1124, 63, 72, 16);
        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 12));

        lblOrden = new JLabel();
        getContentPane().add(lblOrden);
        lblOrden.setBounds(737, 63, 144, 16);
        lblOrden.setFont(new java.awt.Font("Century Gothic", 1, 12));

        lblFecha = new JLabel();
        getContentPane().add(lblFecha);
        lblFecha.setBounds(958, 64, 145, 15);
        lblFecha.setFont(new java.awt.Font("Century Gothic", 1, 12));

        spOrdenes = new JScrollPane();
        getContentPane().add(spOrdenes);
        spOrdenes.setBounds(12, 56, 640, 450);

        tbOrdenes = new JTable();
        tbOrdenes.setRowHeight(28);
        tbOrdenes.setDefaultRenderer(Object.class, new DetalleOrdenRenderer());
        spOrdenes.setViewportView(tbOrdenes);
        tbOrdenes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    VerDetalle();
                }
            }
        });

        jLabel7 = new JLabel();
        getContentPane().add(jLabel7);
        jLabel7.setText("DETALLE DE LA ORDEN");
        jLabel7.setBounds(683, 24, 198, 16);
        jLabel7.setFont(new Font("Century Gothic", Font.BOLD, 18));
        jLabel7.setForeground(Color.BLACK);

        jLabel8 = new JLabel();
        getContentPane().add(jLabel8);
        jLabel8.setText("F.ENTREGA");
        jLabel8.setBounds(683, 398, 97, 16);
        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 12));

        dpFechaEntrega = new JXDatePicker();
        getContentPane().add(dpFechaEntrega);
        dpFechaEntrega.setBounds(780, 393, 543, 28);

        ListarOrdenes();
        Action detail = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerDetalle();
            }
        };
        ButtonColumn buttonColumnAdd = new ButtonColumn(tbOrdenes, detail, 4);
        buttonColumnAdd.setMnemonic(KeyEvent.VK_D);

        container = this;
    }

    public void VerDetalle() {
        int fila = tbOrdenes.getSelectedRow();
        Orden o = controller.ObtenerOrden(modeloOrden.getValueAt(fila, 0).toString());
        if (o != null) {
            lblOrden.setText(String.format("%05d", o.getID()));
            lblFecha.setText(o.getFECHA());
            lblStatus.Colorear(o.getESTADO().getDESCRIPCION());
            ListarDetalle(o.getDETALLE());
            btnProcesar.setEnabled(true);
            btnAnular.setEnabled(true);
        } else {
            Util.Mensaje("No hay información para mostrar.", "Orden no encontrada", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void Cerrar() {
        this.dispose();
    }

    public void ListarOrdenes() {
        try {
            ArrayList<Orden> arr = controller.ObtenerOrdenes(Estado.GENERADA, srcBuscador.getText());
            modeloOrden.setRowCount(0);
            arr.forEach((x) -> {
                modeloOrden.addRow(new Object[]{x.getID(), x.getFECHA(), x.getUSUARIO(), x.getESTADO().getDESCRIPCION(), new ImageIcon("img/detail.png")});
            });
            tbOrdenes.setModel(modeloOrden);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ListarDetalle(ArrayList<Detalle_Orden> arr) {
        try {
            modelo.setRowCount(0);
            arr.forEach((_item) -> {
                modelo.addRow(new Object[]{_item.getPRODUCTO().getID(), _item.getPRODUCTO().getDESCRIPCION(), _item.getPRODUCTO().getPRECIO(), _item.getCANTIDAD(), Util.RoundedValue(_item.getPRODUCTO().getPRECIO() * _item.getCANTIDAD())});
            });
            tbDetalle.setModel(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ImprimirOrden() {
        ArrayList<Orden> arrOrden = new ArrayList<Orden>();
        arrOrden.add(oOrden);
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("rep/Orden_Compra.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null,
                    new JRBeanCollectionDataSource(arrOrden));
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.show();
            viewer.toFront();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dispose();
    }

}

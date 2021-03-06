package UI;

import COMMON.ButtonColumn;
import COMMON.CheckDetailEditor;
import COMMON.CheckDetailRenderer;
import COMMON.DetalleOrdenRenderer;
import COMMON.EstadoLabel;
import COMMON.JIconTextField;
import COMMON.MyCustomButton;
import DAO.PedidoController;
import MODEL.Detalle_Orden;
import MODEL.Estado;
import MODEL.Kardex;
import MODEL.Orden;
import MODEL.Proveedor;
import UTIL.Util;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXSearchField;

public class Recibir_Orden extends JInternalFrame {

    private JTextField srcBuscador;
    private JIconTextField txtFacturaAsociada;
    private JTable tbOrdenes;
    private JLabel jLabel7;
    private JScrollPane spOrdenes;
    private JTextArea txtObs;
    private JLabel lblStatus;
    private JLabel lblFecha;
    private JLabel lblOrden;
    private JLabel jLabel6;
    private JLabel jLabel5;
    private JLabel jLabel4;
    private JButton btnProcesar;
    private JScrollPane spObs;
    private JLabel jLabel3;
    private JLabel jLabel2;
    private JScrollPane scpPedidos;
    private JLabel jLabel1;
    private JTable tbDetalle;
    private JInternalFrame container;
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

    PedidoController controller = new PedidoController();

    DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"", "ID", "DESCRIPCION", "PRECIO", "ACTUAL", "PEDIDO", "RECIBIDO", "IMPORTE", "OBSERVACIONES", "ORDEN_ID"}) {
        @Override
        public boolean isCellEditable(int rowIndex, int colIndex) {
            return colIndex == 0 || colIndex == 6 || colIndex == 8;
        }
    };

    DefaultTableModel modeloOrden = new DefaultTableModel(null, new String[]{"ID", "ENTREGA ESTIMADA", "USUARIO", "ID_PRV", "ESTADO", "PROVEEDOR", ""}) {
        @Override
        public boolean isCellEditable(int rowIndex, int colIndex) {
            return colIndex == 6;
        }
    };

    public Recibir_Orden() {
        this.setLayout(null);
        this.setVisible(true);
        this.setClosable(true);
        this.setTitle(".:: RECIBIR ORDEN ::.");
        this.setSize(new java.awt.Dimension(1360, 650));

        srcBuscador = new JXSearchField();
        getContentPane().add(srcBuscador);
        srcBuscador.setBounds(210, 21, 442, 23);
        srcBuscador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                ListarOrdenes();
            }
        });

        jLabel1 = new JLabel();
        getContentPane().add(jLabel1);
        jLabel1.setText("BUSCAR POR ORDEN");
        jLabel1.setFont(new Font("Century Gothic", Font.BOLD, 18));
        jLabel1.setForeground(Color.BLACK);
        jLabel1.setBounds(12, 24, 198, 16);

        tbDetalle = new JTable(modelo);
        tbDetalle.setDefaultRenderer(Object.class, new CheckDetailRenderer());
        tbDetalle.setDefaultEditor(Object.class, new CheckDetailEditor());
        tbDetalle.removeColumn(tbDetalle.getColumnModel().getColumn(4));
        tbDetalle.removeColumn(tbDetalle.getColumnModel().getColumn(8));
        tbDetalle.setRowHeight(28);
        scpPedidos = new JScrollPane();
        getContentPane().add(scpPedidos);
        scpPedidos.setViewportView(tbDetalle);
        scpPedidos.setBounds(684, 107, 640, 227);

        jLabel2 = new JLabel();
        getContentPane().add(jLabel2);
        jLabel2.setText("FACTURA");
        jLabel2.setBounds(683, 356, 97, 16);
        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 12));

        txtFacturaAsociada = new JIconTextField();
        getContentPane().add(txtFacturaAsociada);
        txtFacturaAsociada.setBounds(780, 353, 543, 28);
        txtFacturaAsociada.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent fe) {
                int fila = tbOrdenes.getSelectedRow();
                boolean flag = controller.ValidarFactura(modeloOrden.getValueAt(fila, 3).toString(), txtFacturaAsociada.getText());
                if (flag) {
                    txtFacturaAsociada.setIcon(new ImageIcon("img/alert.png"));
                    txtFacturaAsociada.setOrientation(SwingConstants.LEFT);
                    btnProcesar.setEnabled(false);
                } else {
                    txtFacturaAsociada.setIcon(new ImageIcon("img/ok.png"));
                    txtFacturaAsociada.setOrientation(SwingConstants.LEFT);
                    btnProcesar.setEnabled(true);
                }

            }
        });

        jLabel3 = new JLabel();
        getContentPane().add(jLabel3);
        jLabel3.setText("OBSERVACION");
        jLabel3.setBounds(683, 395, 97, 16);
        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12));

        spObs = new JScrollPane();
        getContentPane().add(spObs);
        spObs.setBounds(780, 395, 544, 110);

        txtObs = new JTextArea();
        txtObs.setFont(new Font("Century Gothic", Font.BOLD, 14));
        spObs.setViewportView(txtObs);

        btnProcesar = new MyCustomButton("img/grabar.png", "GRABAR", false);
        getContentPane().add(btnProcesar);
        btnProcesar.setEnabled(false);
        btnProcesar.setBounds(1250, 525, 70, 70);
        btnProcesar.addActionListener((ActionEvent ae) -> {
            Orden o = new Orden();
            if (ProcesarStock(o)) {
                o.setID(Integer.parseInt(lblOrden.getText()));
                o.setFACTURA(txtFacturaAsociada.getText());
                o.setOBS(txtObs.getText());
                Proveedor p = new Proveedor();
                int fila = tbOrdenes.getSelectedRow();
                p.setID(Integer.parseInt(modeloOrden.getValueAt(fila, 3).toString()));
                o.setFECHA_ENTREGA(modeloOrden.getValueAt(fila, 1).toString());
                o.setPROVEEDOR(p);
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
        spOrdenes.setBounds(12, 56, 640, 540);
        tbOrdenes = new JTable(modeloOrden);
        tbOrdenes.setDefaultRenderer(Object.class, new DetalleOrdenRenderer());
        tbOrdenes.setRowHeight(28);
        tbOrdenes.removeColumn(tbOrdenes.getColumnModel().getColumn(3));
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

        ListarOrdenes();
        Action detail = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerDetalle();
            }
        };
        ButtonColumn buttonColumnAdd = new ButtonColumn(tbOrdenes, detail, 5);
        buttonColumnAdd.setMnemonic(KeyEvent.VK_D);

        container = this;
    }

    public void VerDetalle() {
        int fila = tbOrdenes.getSelectedRow();
        Orden o = controller.ObtenerOrden(modeloOrden.getValueAt(fila, 0).toString());
        if (o != null) {
            lblOrden.setText(String.format("%05d", o.getID()));
            lblFecha.setText(o.getFECHA());
            lblStatus = new EstadoLabel(o.getESTADO().getDESCRIPCION());
            getContentPane().add(lblStatus);
            lblStatus.setBounds(1176, 64, 147, 15);
            ListarDetalle(o.getDETALLE());
        } else {
            Util.Mensaje("No hay información para mostrar.", "Orden no encontrada", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void ListarOrdenes() {
        try {
            ArrayList<Orden> arr = controller.ObtenerOrdenes(Estado.PROCESADA, srcBuscador.getText());
            modeloOrden.setRowCount(0);
            arr.forEach((x) -> {
                modeloOrden.addRow(new Object[]{x.getID(), x.getFECHA_ENTREGA(), x.getUSUARIO(), x.getPROVEEDOR().getID(), x.getESTADO().getDESCRIPCION(), x.getPROVEEDOR().getRAZON_SOCIAL(), new ImageIcon("img/detail.png")});
            });
            tbOrdenes.setModel(modeloOrden);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Cerrar() {
        this.dispose();
    }

    public void ListarDetalle(ArrayList<Detalle_Orden> arr) {
        try {
            modelo.setRowCount(0);
            arr.forEach((_item) -> {
                modelo.addRow(new Object[]{0, _item.getPRODUCTO().getID(), _item.getPRODUCTO().getDESCRIPCION(), _item.getPRODUCTO().getPRECIO(), _item.getPRODUCTO().getSTOCK_ACTUAL(), _item.getCANTIDAD(), _item.getCANTIDAD(), Util.RoundedValue(_item.getPRODUCTO().getPRECIO() * _item.getCANTIDAD()), "", _item.getORDEN_ID()});
            });
            tbDetalle.setModel(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean ProcesarStock(Orden o) {
        boolean flag = false;
        o.setESTADO(new Estado(Estado.ENTREGADA));        
        int totalOk = 0;
        int totalKo = 0;
        ArrayList<Kardex> arr = new ArrayList<Kardex>();
        for (int i = 0; i < tbDetalle.getRowCount(); i++) {
            if (Integer.parseInt(modelo.getValueAt(i, 0).toString()) == 1) {
                int cantidad = Integer.parseInt(modelo.getValueAt(i, 5).toString());
                int stock_actual = Integer.parseInt(modelo.getValueAt(i, 4).toString());
                Kardex k = new Kardex(Integer.parseInt(modelo.getValueAt(i, 1).toString()),
                        cantidad,
                        cantidad,
                        "INGRESO",
                        stock_actual,
                        (stock_actual + cantidad),
                        modelo.getValueAt(i, 8).toString(),
                        Integer.parseInt(modelo.getValueAt(i, 9).toString())
                );
                arr.add(k);
                totalOk++;
            } else {
                if (modelo.getValueAt(i, 8).toString().isEmpty()) {
                    Util.Mensaje("Hay productos inválidos, por favor, ingrese las observaciones para el seguimiento respectivo.", "Alerta", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                int cantidad_pedida = Integer.parseInt(modelo.getValueAt(i, 5).toString());
                int cantidad_recibida = Integer.parseInt(modelo.getValueAt(i, 6).toString());
                int stock_actual = Integer.parseInt(modelo.getValueAt(i, 4).toString());
                Kardex k = new Kardex(Integer.parseInt(modelo.getValueAt(i, 1).toString()),
                        cantidad_recibida,
                        cantidad_pedida,
                        "ERROR",
                        stock_actual,
                        (stock_actual + cantidad_recibida),
                        modelo.getValueAt(i, 8).toString(),
                        Integer.parseInt(modelo.getValueAt(i, 9).toString())
                );
                arr.add(k);
                totalKo++;
                o.setESTADO(new Estado(Estado.OBSERVADA));
            }
        }
        controller.GenerarKardex(arr);
        flag = true;
        return flag;
    }
}

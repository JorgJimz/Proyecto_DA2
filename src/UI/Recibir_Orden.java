package UI;

import COMMON.ButtonColumn;
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
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.jdesktop.swingx.JXSearchField;

public class Recibir_Orden extends JInternalFrame {

    private JTextField txtBuscador;
    private JTextField srcBuscador;
    private JFormattedTextField txtFacturaAsociada;
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

    PedidoController controller = new PedidoController();

    DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"ID", "DESCRIPCION", "PRECIO", "CANTIDAD", "IMPORTE"}) {
        @Override
        public boolean isCellEditable(int rowIndex, int colIndex) {
            return false;
        }
    };

    DefaultTableModel modeloOrden = new DefaultTableModel(null, new String[]{"ID", "FECHA", "USUARIO", "ID_PRV", "PROVEEDOR", "ESTADO", ""}) {
        @Override
        public boolean isCellEditable(int rowIndex, int colIndex) {
            return colIndex == 6;
        }
    };

    public Recibir_Orden()  {
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
                //
            }
        });

        jLabel1 = new JLabel();
        getContentPane().add(jLabel1);
        jLabel1.setText("BUSCAR POR ORDEN");
        jLabel1.setFont(new Font("Century Gothic", Font.BOLD, 18));
        jLabel1.setForeground(Color.BLACK);
        jLabel1.setBounds(12, 24, 198, 16);

        tbDetalle = new JTable(modelo);
        scpPedidos = new JScrollPane();
        getContentPane().add(scpPedidos);
        scpPedidos.setViewportView(tbDetalle);
        scpPedidos.setBounds(684, 107, 640, 227);

        jLabel2 = new JLabel();
        getContentPane().add(jLabel2);
        jLabel2.setText("FACTURA");
        jLabel2.setBounds(683, 356, 97, 16);
        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 12));

        try {
            txtFacturaAsociada = new JFormattedTextField(new MaskFormatter("E###-#####"));       
            getContentPane().add(txtFacturaAsociada);
            txtFacturaAsociada.setBounds(780, 353, 543, 23);
        } catch (Exception e) {
        }

        jLabel3 = new JLabel();
        getContentPane().add(jLabel3);
        jLabel3.setText("OBSERVACION");
        jLabel3.setBounds(683, 395, 97, 16);
        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12));

        spObs = new JScrollPane();
        getContentPane().add(spObs);
        spObs.setBounds(780, 395, 544, 110);

        txtObs = new JTextArea();
        spObs.setViewportView(txtObs);

        btnProcesar = new MyCustomButton("img/grabar.png", "GRABAR", false);
        getContentPane().add(btnProcesar);
        btnProcesar.setBounds(1180, 515, 70, 70);
        btnProcesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Orden o = new Orden();
                o.setID(Integer.parseInt(lblOrden.getText()));
                o.setFACTURA(txtFacturaAsociada.getText());
                o.setOBS(txtObs.getText());
                Proveedor p = new Proveedor();
                int fila = tbOrdenes.getSelectedRow();
                p.setID(Integer.parseInt(modeloOrden.getValueAt(fila, 3).toString()));
                o.setPROVEEDOR(p);
                o.setESTADO(new Estado(Estado.ENTREGADA));
                controller.ProcesarOrden(o);
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

        lblStatus = new JLabel();
        getContentPane().add(lblStatus);
        lblStatus.setBounds(1176, 64, 147, 15);
        lblStatus.setFont(new java.awt.Font("Century Gothic", 1, 12));

        spOrdenes = new JScrollPane();
        getContentPane().add(spOrdenes);
        spOrdenes.setBounds(12, 56, 640, 450);

        tbOrdenes = new JTable(modeloOrden);
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
    }

    public void VerDetalle() {
        int fila = tbOrdenes.getSelectedRow();
        Orden o = controller.ObtenerOrden(modeloOrden.getValueAt(fila, 0).toString());
        if (o != null) {
            lblOrden.setText(String.format("%05d", o.getID()));
            lblFecha.setText(o.getFECHA());
            lblStatus.setText(o.getESTADO().getDESCRIPCION());
            ListarDetalle(o.getDETALLE());
        } else {
            Util.Mensaje("No hay información para mostrar.", "Orden no encontrada", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void ListarOrdenes() {
        try {
            ArrayList<Orden> arr = controller.ObtenerOrdenes(Estado.PROCESADA);
            modeloOrden.setRowCount(0);
            arr.forEach((x) -> {
                modeloOrden.addRow(new Object[]{x.getID(), x.getFECHA(), x.getUSUARIO(), x.getPROVEEDOR().getID(), x.getPROVEEDOR().getRAZON_SOCIAL(), x.getESTADO().getDESCRIPCION(), new ImageIcon("img/detail.png")});
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
                modelo.addRow(new Object[]{_item.getPRODUCTO().getID(), _item.getPRODUCTO().getDESCRIPCION(), _item.getPRODUCTO().getPRECIO(), _item.getCANTIDAD(), _item.getPRECIO() * _item.getCANTIDAD()});
            });
            tbDetalle.setModel(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

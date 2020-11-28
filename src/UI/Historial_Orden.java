package UI;

import COMMON.EstadoLabel;
import DAO.PedidoController;
import MODEL.Detalle_Orden;
import MODEL.Orden;
import UTIL.Util;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Historial_Orden extends JInternalFrame {

    private JTextField schOrden;
    private JLabel jLabel3;
    private JLabel jLabel10;
    private JLabel lblTotalItems;
    private JLabel lblValorCompra;
    private JLabel jLabel5;
    private JLabel jLabel2;
    private JLabel lblNumeroOrden;
    private JLabel jLabel4;
    private JButton jButton1;
    private JButton btnImprimir;
    private JPanel pnlInfoOrden;
    private JLabel lblObs;
    private JLabel lblFactura;
    private JLabel lblProveedor;
    private EstadoLabel lblStatus;
    private JLabel lblFecha;
    private JLabel jLabel9;
    private JLabel jLabel8;
    private JLabel jLabel7;
    private JLabel jLabel6;
    private JScrollPane scpHistorial;
    private JScrollPane scpDetalle;
    private JLabel jLabel1;
    private JTable tbHistorial;
    private JTable tbDetalle;

    DefaultTableModel modeloDetalle = new DefaultTableModel(null, new String[]{"ID", "DESCRIPCION", "PRECIO",
        "ANTERIOR", "PEDIDO", "RECIBIDO", "ACTUAL", "OBSERVACIONES"}) {
        @Override
        public boolean isCellEditable(int rowIndex, int colIndex) {
            return false;
        }
    };

    DefaultTableModel modeloHistorial = new DefaultTableModel(null,
            new String[]{"ID", "FECHA", "USUARIO", "ID_PRV", "PROVEEDOR", "ESTADO", ""}) {
        @Override
        public boolean isCellEditable(int rowIndex, int colIndex) {
            return colIndex == 6;
        }
    };

    PedidoController controller = new PedidoController();

    public Historial_Orden() {
        this.setSize(1038, 735);
        this.setLayout(null);
        this.setVisible(true);
        this.setClosable(true);

        schOrden = new JTextField();
        getContentPane().add(schOrden);
        schOrden.setBounds(97, 14, 751, 28);
        schOrden.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    VerDetalle();
                }
            }
        });

        pnlInfoOrden = new JPanel();
        GridLayout pnlInfoOrdenLayout = new GridLayout(1, 1);
        pnlInfoOrdenLayout.setColumns(2);
        pnlInfoOrdenLayout.setRows(8);
        pnlInfoOrdenLayout.setHgap(5);
        pnlInfoOrdenLayout.setVgap(5);
        pnlInfoOrden.setLayout(pnlInfoOrdenLayout);
        getContentPane().add(pnlInfoOrden);
        pnlInfoOrden.setBorder(BorderFactory.createTitledBorder(null, "INFORMACION DE LA ORDEN", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, new Font("Century Gothic", 1, 18), Color.BLACK));

        pnlInfoOrden.setBounds(12, 53, 439, 440);

        jLabel4 = new JLabel();
        pnlInfoOrden.add(jLabel4);
        jLabel4.setText("ORDEN");
        jLabel4.setBounds(28, 98, 111, 16);
        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 12));

        lblNumeroOrden = new JLabel();
        pnlInfoOrden.add(lblNumeroOrden);
        lblNumeroOrden.setBounds(151, 98, 290, 16);

        jLabel3 = new JLabel();
        pnlInfoOrden.add(jLabel3);
        jLabel3.setText("FECHA");
        jLabel3.setBounds(27, 140, 111, 16);
        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12));

        lblFecha = new JLabel();
        pnlInfoOrden.add(lblFecha);
        lblFecha.setBounds(150, 140, 290, 16);

        jLabel6 = new JLabel();
        pnlInfoOrden.add(jLabel6);
        jLabel6.setText("STATUS");
        jLabel6.setBounds(27, 182, 111, 16);
        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 12));
                
        lblStatus = new EstadoLabel();
        pnlInfoOrden.add(lblStatus);

        jLabel7 = new JLabel();
        pnlInfoOrden.add(jLabel7);
        jLabel7.setText("PROVEEDOR");
        jLabel7.setBounds(27, 224, 111, 16);
        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 12));

        lblProveedor = new JLabel();
        pnlInfoOrden.add(lblProveedor);
        lblProveedor.setBounds(150, 224, 290, 16);

        jLabel8 = new JLabel();
        pnlInfoOrden.add(jLabel8);
        jLabel8.setText("FACTURA");
        jLabel8.setBounds(27, 266, 111, 16);
        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 12));

        lblFactura = new JLabel();
        pnlInfoOrden.add(lblFactura);
        lblFactura.setBounds(150, 266, 290, 16);

        jLabel9 = new JLabel();
        pnlInfoOrden.add(jLabel9);
        jLabel9.setText("OBSERVACION");
        jLabel9.setBounds(27, 311, 111, 16);
        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 12));

        lblObs = new JLabel();
        pnlInfoOrden.add(lblObs);
        lblObs.setBounds(150, 308, 290, 16);

        jLabel2 = new JLabel();
        pnlInfoOrden.add(jLabel2);
        jLabel2.setText("VALOR COMPRA");
        jLabel2.setBounds(29, 357, 111, 16);
        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 12));

        lblValorCompra = new JLabel();
        pnlInfoOrden.add(lblValorCompra);
        lblValorCompra.setBounds(146, 359, 290, 16);

        jLabel5 = new JLabel();
        pnlInfoOrden.add(jLabel5);
        jLabel5.setText("TOTAL ITEMS");
        jLabel5.setBounds(27, 403, 111, 16);
        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 12));

        lblTotalItems = new JLabel();
        pnlInfoOrden.add(lblTotalItems);
        lblTotalItems.setBounds(144, 404, 290, 16);

        jLabel1 = new JLabel();
        getContentPane().add(jLabel1);
        jLabel1.setText("NRO ORDEN");
        jLabel1.setBounds(12, 13, 73, 28);
        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 12));

        scpDetalle = new JScrollPane();
        getContentPane().add(scpDetalle);
        scpDetalle.setBounds(12, 505, 998, 179);

        scpHistorial = new JScrollPane();
        getContentPane().add(scpHistorial);
        scpHistorial.setBounds(463, 92, 547, 401);

        btnImprimir = new JButton();
        getContentPane().add(btnImprimir);
        btnImprimir.setText("IMPRIMIR ORDEN");
        btnImprimir.setBounds(941, 11, 70, 70);

        jButton1 = new JButton();
        getContentPane().add(jButton1);
        jButton1.setText("IMPRIMIR HISTORIAL");
        jButton1.setBounds(860, 11, 70, 70);

        jLabel10 = new JLabel();
        getContentPane().add(jLabel10);
        jLabel10.setText("HISTORIAL");
        jLabel10.setBounds(463, 54, 385, 32);
        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 18));

        tbDetalle = new JTable(modeloDetalle);
        tbDetalle.setRowHeight(28);
        scpDetalle.setViewportView(tbDetalle);

        tbHistorial = new JTable(modeloHistorial);
        tbHistorial.setRowHeight(28);
        scpHistorial.setViewportView(tbHistorial);

    }

    public void VerDetalle() {
        Orden o = controller.ObtenerOrden(schOrden.getText());
        if (o != null) {
            lblNumeroOrden.setText(String.format("%05d", o.getID()));
            lblFecha.setText(o.getFECHA());
            lblProveedor.setText(o.getPROVEEDOR().getRAZON_SOCIAL());
            lblFactura.setText(o.getFACTURA());
            lblStatus.Colorear(o.getESTADO().getDESCRIPCION());
            lblObs.setText(o.getOBS());
            lblValorCompra.setText("1000");
            lblTotalItems.setText("" + o.getDETALLE().size());
            ListarDetalle(o.getDETALLE());
        } else {
            Util.Mensaje(
                    "No hay información para mostrar.", "Orden no encontrada",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void ListarDetalle(ArrayList<Detalle_Orden> arr) {
        try {
            modeloDetalle.setRowCount(0);
            arr.forEach((x) -> {
                modeloDetalle.addRow(new Object[]{x.getPRODUCTO().getID(),
                    x.getPRODUCTO().getDESCRIPCION(), x.getPRODUCTO().getPRECIO(),
                    x.getKARDEX().getSTOCK_ANTERIOR(), x.getKARDEX().getCANTIDAD_PEDIDA(),
                    x.getKARDEX().getCANTIDAD_RECIBIDA(), x.getKARDEX().getNUEVO_STOCK(),
                    x.getKARDEX().getOBSERVACION()});
            });
            tbDetalle.setModel(modeloDetalle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

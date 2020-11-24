package UI;

import MODEL.Orden;
import UTIL.Util;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Historial_Orden extends JFrame {

    private JTextField schOrden;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JButton jButton1;
    private JButton btnImprimir;
    private JSeparator jSeparator2;
    private JSeparator jSeparator1;
    private JLabel jLabel10;
    private JLabel lblObs;
    private JLabel lblFactura;
    private JLabel lblProveedor;
    private JLabel lblStatus;
    private JLabel lblFecha;
    private JLabel jLabel9;
    private JLabel jLabel8;
    private JLabel jLabel7;
    private JLabel jLabel6;
    private JLabel jLabel5;
    private JLabel jLabel4;
    private JScrollPane scpHistorial;
    private JScrollPane scpDetalle;
    private JLabel jLabel1;
    private JTable tbHistorial;
    private JTable tbDetalle;
    
    
    DefaultTableModel modeloOrden = new DefaultTableModel(null, new String[]{"ID", "FECHA", "USUARIO", "ID_PRV", "PROVEEDOR", "ESTADO", ""}) {
        @Override
        public boolean isCellEditable(int rowIndex, int colIndex) {
            return colIndex == 6;
        }
    };
    
    /* DefaultTableModel modeloOrden = new DefaultTableModel(null, new String[]{"ID", "FECHA", "USUARIO", "ID_PRV", "PROVEEDOR", "ESTADO", ""}) {
        @Override
        public boolean isCellEditable(int rowIndex, int colIndex) {
            return colIndex == 6;
        }
    };*/

    private void Historial_Orden() {

        this.setSize(952, 659);
        this.setLayout(null);

        schOrden = new JTextField();
        getContentPane().add(schOrden);
        schOrden.setBounds(103, 14, 387, 23);

        jLabel1 = new JLabel();
        getContentPane().add(jLabel1);
        jLabel1.setText("NRO ORDEN");
        jLabel1.setBounds(12, 17, 73, 16);
        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 12));

        jLabel2 = new JLabel();
        getContentPane().add(jLabel2);
        jLabel2.setText("Historial");
        jLabel2.setBounds(504, 407, 118, 25);
        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18));

        scpDetalle = new JScrollPane();
        getContentPane().add(scpDetalle);
        scpDetalle.setBounds(12, 49, 480, 383);

        scpHistorial = new JScrollPane();
        getContentPane().add(scpHistorial);
        scpHistorial.setBounds(502, 448, 416, 150);

        jLabel3 = new JLabel();
        getContentPane().add(jLabel3);
        jLabel3.setText("FECHA");
        jLabel3.setBounds(502, 61, 111, 16);
        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12));

        jLabel4 = new JLabel();
        getContentPane().add(jLabel4);
        jLabel4.setText("TOTAL");
        jLabel4.setBounds(12, 447, 397, 16);
        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 12));

        jLabel5 = new JLabel();
        getContentPane().add(jLabel5);
        jLabel5.setText("CONTEO");
        jLabel5.setBounds(12, 501, 397, 16);
        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 12));

        jLabel6 = new JLabel();
        getContentPane().add(jLabel6);
        jLabel6.setText("STATUS");
        jLabel6.setBounds(502, 106, 111, 16);
        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 12));

        jLabel7 = new JLabel();
        getContentPane().add(jLabel7);
        jLabel7.setText("PROVEEDOR");
        jLabel7.setBounds(502, 151, 111, 16);
        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 12));

        jLabel8 = new JLabel();
        getContentPane().add(jLabel8);
        jLabel8.setText("FACTURA");
        jLabel8.setBounds(502, 196, 111, 16);
        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 12));

        jLabel9 = new JLabel();
        getContentPane().add(jLabel9);
        jLabel9.setText("OBSERVACIONES");
        jLabel9.setBounds(502, 242, 111, 16);
        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 12));

        lblFecha = new JLabel();
        getContentPane().add(lblFecha);
        lblFecha.setBounds(625, 61, 290, 16);
        lblFecha.setText("a");

        lblStatus = new JLabel();
        getContentPane().add(lblStatus);
        lblStatus.setText("b");
        lblStatus.setBounds(625, 105, 290, 16);

        lblProveedor = new JLabel();
        getContentPane().add(lblProveedor);
        lblProveedor.setText("c");
        lblProveedor.setBounds(625, 150, 290, 16);

        lblFactura = new JLabel();
        getContentPane().add(lblFactura);
        lblFactura.setText("d");
        lblFactura.setBounds(625, 195, 290, 16);

        lblObs = new JLabel();
        getContentPane().add(lblObs);
        lblObs.setText("e");
        lblObs.setBounds(502, 270, 413, 119);

        jLabel10 = new JLabel();
        getContentPane().add(jLabel10);
        jLabel10.setText("Informaci�n de la Orden");
        jLabel10.setBounds(502, 12, 420, 25);
        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 18));

        jSeparator1 = new JSeparator();
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(504, 432, 415, 10);

        jSeparator2 = new JSeparator();
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(502, 48, 422, 10);

        btnImprimir = new JButton();
        getContentPane().add(btnImprimir);
        btnImprimir.setText("IMPRIMIR ORDEN");
        btnImprimir.setBounds(421, 447, 70, 70);

        jButton1 = new JButton();
        getContentPane().add(jButton1);
        jButton1.setText("IMPRIMIR HISTORIAL");
        jButton1.setBounds(421, 528, 70, 70);

        scpDetalle = new JScrollPane();
        getContentPane().add(scpDetalle);
        scpDetalle.setBounds(12, 49, 480, 383);
       
        tbDetalle = new JTable();
        scpDetalle.setViewportView(tbDetalle);
        tbDetalle.setModel(modeloOrden);

        scpHistorial = new JScrollPane();
        getContentPane().add(scpHistorial);
        scpHistorial.setBounds(502, 448, 416, 150);
       
        tbHistorial = new JTable();
        scpHistorial.setViewportView(tbHistorial);
        //tbHistorial.setModel(tbHistorialModel);

    }

   /* public void VerDetalle() {
        int fila = .getSelectedRow();
        Orden o = controller.ObtenerOrden(modeloOrden.getValueAt(fila, 0).toString());
        if (o != null) {
            lblOrden.setText(String.format("%05d", o.getID()));
            lblFecha.setText(o.getFECHA());
            lblStatus.setText(o.getESTADO().getDESCRIPCION());
            ListarDetalle(o.getDETALLE());
        } else {
            Util.Mensaje("No hay información para mostrar.", "Orden no encontrada", JOptionPane.WARNING_MESSAGE);
        }
    }*/

}

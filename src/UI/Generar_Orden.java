package UI;

import COMMON.ButtonColumn;
import COMMON.EmailSenderService;
import COMMON.MyCustomButton;
import COMMON.ProductoRenderer;
import DAO.PedidoController;
import MODEL.Detalle_Orden;
import MODEL.Orden;
import MODEL.Producto;
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
import javax.swing.JInternalFrame;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXSearchField;

public class Generar_Orden extends JInternalFrame {

    private JScrollPane spTabla;
    private JTable tbProducto;
    private JLabel jLabel1;
    private JXSearchField txtBuscador;
    private JButton btGenerar;
    private JTextArea txtObs;
    private JScrollPane spObs;
    private JLabel jLabel2;
    private JLabel lblTotal;
    private JScrollPane spProducto;
    private JTable tbProducto2;
    private JCheckBox chkUrgente;
    private ArrayList<Detalle_Orden> arrDetalle;

    PedidoController controller = new PedidoController();

    DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"ID", "DESCRIPCION", "PRECIO", "UNIDAD MEDIDA", "STK ACTUAL", "STK MINIMO", ""}) {
        @Override
        public boolean isCellEditable(int rowIndex, int colIndex) {
            if (colIndex == 6) {
                return true;
            }
            return false;
        }
    };

    DefaultTableModel modelo2 = new DefaultTableModel(null, new String[]{"ID", "DESCRIPCION", "PRECIO", "CANTIDAD", "IMPORTE", ""}) {
        @Override
        public boolean isCellEditable(int rowIndex, int colIndex) {
            if (colIndex == 3 || colIndex == 5) {
                return true;
            }
            return false;
        }
    };

    public Generar_Orden() {
        arrDetalle = new ArrayList<>();
        this.setVisible(true);
        this.setClosable(true);
        this.setTitle(".:: GENERAR ORDEN ::.");
        this.setLayout(null);
        this.setBounds(0, 0, 1321, 569);
        this.setPreferredSize(new java.awt.Dimension(1321, 569));

        chkUrgente = new JCheckBox("SOLO URGENTES");
        chkUrgente.setBounds(508, 40, 150, 23);
        chkUrgente.setFont(new Font("Century Gothic", Font.BOLD, 14));
        chkUrgente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ListarProductos();
            }
        });
        getContentPane().add(chkUrgente);

        spTabla = new JScrollPane();
        getContentPane().add(spTabla);
        spTabla.setBounds(12, 84, 626, 436);

        tbProducto = new JTable();
        spTabla.setViewportView(tbProducto);
        tbProducto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    AgregarDetalle();
                }
            }
        });

        tbProducto.setModel(modelo);
        tbProducto.setRowHeight(28);
        tbProducto.setDefaultRenderer(Object.class, new ProductoRenderer());
        txtBuscador = new JXSearchField("Buscar por DESCRIPCION");
        txtBuscador.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
        getContentPane().add(txtBuscador);
        txtBuscador.setBounds(12, 40, 490, 23);
        jLabel1 = new JLabel();
        getContentPane().add(jLabel1);
        jLabel1.setText("LISTA DE PRODUCTOS");
        jLabel1.setFont(new Font("Century Gothic", Font.BOLD, 18));
        jLabel1.setForeground(Color.BLACK);
        jLabel1.setBounds(12, 12, 200, 16);
        txtBuscador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                ListarProductos();
            }
        });

        spProducto = new JScrollPane();
        getContentPane().add(spProducto);
        spProducto.setBounds(667, 43, 628, 317);

        tbProducto2 = new JTable();
        spProducto.setViewportView(tbProducto2);
        tbProducto2.setModel(modelo2);
        tbProducto2.setRowHeight(28);
        tbProducto2.getModel().addTableModelListener((TableModelEvent tme) -> {
            if (tme.getType() == TableModelEvent.UPDATE && tme.getColumn() == 3) {
                int fila = tbProducto2.getSelectedRow();
                int id = Integer.parseInt(tbProducto2.getValueAt(fila, 0).toString());
                int cantidad = Integer.parseInt(tbProducto2.getValueAt(fila, 3).toString());
                Detalle_Orden d = arrDetalle.stream().filter(x -> x.getPRODUCTO_ID() == id).findFirst().get();
                d.setCANTIDAD(cantidad);
                d.setTOTAL(d.getPRECIO() * d.getCANTIDAD());
                CalcularTotal();
                ListarDetalle();
            }
        });

        btGenerar = new MyCustomButton("img/grabar.png", "GRABAR", false);
        getContentPane().add(btGenerar);
        btGenerar.setEnabled(false);
        btGenerar.setBounds(1225, 396, 70, 70);
        btGenerar.addActionListener((ActionEvent ae) -> {
            Orden o = controller.GenerarPedido(arrDetalle, txtObs.getText());
            new EmailSenderService().sendEmail(o.getID());
            Cerrar();
        });

        lblTotal = new JLabel();
        getContentPane().add(lblTotal);
        lblTotal.setText("TOTAL: S/.");
        lblTotal.setFont(new Font("Century Gothic", Font.BOLD, 18));
        lblTotal.setForeground(Color.BLACK);
        lblTotal.setBounds(667, 368, 628, 16);

        jLabel2 = new JLabel();
        getContentPane().add(jLabel2);
        jLabel2.setText("DETALLE DEL PEDIDO");
        jLabel2.setFont(new Font("Century Gothic", Font.BOLD, 18));
        jLabel2.setForeground(Color.BLACK);
        jLabel2.setBounds(667, 12, 200, 16);

        spObs = new JScrollPane();
        getContentPane().add(spObs);
        spObs.setBounds(667, 396, 546, 124);

        txtObs = new JTextArea();
        txtObs.setFont(new Font("Century Gothic", Font.BOLD, 14));
        spObs.setViewportView(txtObs);

        ListarProductos();
        Action add = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarDetalle();
            }
        };

        Action delete = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RetirarDetalle();
            }
        };

        ButtonColumn buttonColumnAdd = new ButtonColumn(tbProducto, add, 6);
        buttonColumnAdd.setMnemonic(KeyEvent.VK_E);

        ButtonColumn buttonColumnDel = new ButtonColumn(tbProducto2, delete, 5);
        buttonColumnDel.setMnemonic(KeyEvent.VK_D);
    }

    public void Cerrar() {
        this.dispose();
    }

    public void CalcularTotal() {
        double total = 0;
        for (Detalle_Orden d : arrDetalle) {
            total += d.getTOTAL();
        }
        lblTotal.setText("Total: S/." + Util.RoundedValue(total));
    }

    public void ListarDetalle() {
        modelo2.setRowCount(0);
        for (Detalle_Orden d : arrDetalle) {
            modelo2.addRow(new Object[]{d.getPRODUCTO_ID(), d.getNOMBRE(), d.getPRECIO(), d.getCANTIDAD(), Util.RoundedValue(d.getTOTAL()), new ImageIcon("img/del.png")});
        }
        tbProducto2.setModel(modelo2);
        btGenerar.setEnabled(!arrDetalle.isEmpty());
    }

    public void AgregarDetalle() {
        int fila = tbProducto.getSelectedRow();
        int id = Integer.parseInt(tbProducto.getValueAt(fila, 0).toString());
        Detalle_Orden obj = arrDetalle.stream().filter(x -> x.getPRODUCTO_ID() == id).findFirst().orElse(null);
        if (obj == null) {
            String nombre = tbProducto.getValueAt(fila, 1).toString();
            double precio = Double.parseDouble(tbProducto.getValueAt(fila, 2).toString());
            Detalle_Orden nuevoDetalle = new Detalle_Orden();
            nuevoDetalle.setPRODUCTO_ID(id);
            nuevoDetalle.setPRECIO(precio);
            nuevoDetalle.setNOMBRE(nombre);
            nuevoDetalle.setCANTIDAD(0);
            nuevoDetalle.setTOTAL(0);
            arrDetalle.add(nuevoDetalle);
            CalcularTotal();
            ListarDetalle();
        } else {
            Util.Mensaje("Este producto ya se encuentra en el detalle del pedido.", "Duplicado", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void RetirarDetalle() {
        int fila = tbProducto2.getSelectedRow();
        int id = Integer.parseInt(tbProducto2.getValueAt(fila, 0).toString());
        int option = JOptionPane.showConfirmDialog(null, "<html><h4><b>¿Seguro de eliminar este detalle?</b></h4></html>", "Mensaje de Alerta",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            for (Detalle_Orden d : arrDetalle) {
                if (d.getPRODUCTO_ID() == id) {
                    arrDetalle.remove(d);
                    break;
                }
            }
            Util.Mensaje("Detalle eliminado.", "Mensaje del Sistemas", JOptionPane.INFORMATION_MESSAGE);
            CalcularTotal();
            ListarDetalle();
        }
    }

    public void ListarProductos() {
        try {
            ArrayList<Producto> arrProd = controller.CargarProductos(txtBuscador.getText(), chkUrgente.isSelected());
            modelo.setRowCount(0);
            arrProd.forEach((x) -> {
                modelo.addRow(new Object[]{x.getID(), x.getDESCRIPCION(), x.getPRECIO(), x.getUNIDAD_MEDIDA(), x.getSTOCK_ACTUAL(), x.getSTOCK_MINIMO(), new ImageIcon("img/add.png")});
            });
            tbProducto.setModel(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

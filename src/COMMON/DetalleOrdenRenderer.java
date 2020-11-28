package COMMON;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class DetalleOrdenRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        JLabel e = new JLabel();
        e.setOpaque(true);
        e.setText(value.toString());

        if (column == 3) {
            if (value.toString().equalsIgnoreCase(Estado.GENERADA.toString())) {
                e = new EstadoLabel(Estado.GENERADA.toString());
            } else if (value.toString().equalsIgnoreCase(Estado.PROCESADA.toString())) {
                e = new EstadoLabel(Estado.PROCESADA.toString());
            } else if (value.toString().equalsIgnoreCase(Estado.ANULADA.toString())) {
                e = new EstadoLabel(Estado.ANULADA.toString());
            } else {
                //ENTREGADA
                e = new EstadoLabel(Estado.ENTREGADA.toString());
            }
        }

        if (isSelected) {
            e.setBackground(new Color(228, 1, 55));
            e.setForeground(Color.WHITE);
        }

        return e;
    }

}

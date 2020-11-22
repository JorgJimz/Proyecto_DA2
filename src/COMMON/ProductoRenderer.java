package COMMON;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ProductoRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        JLabel e = new JLabel();
        e.setOpaque(true);
        e.setText(value.toString());

        if (isSelected) {
            e.setBackground(new Color(228, 1, 55));
            e.setForeground(Color.WHITE);
        }

        int stockActual = Integer.parseInt(table.getModel().getValueAt(row, 4).toString());
        int stockMinimo = Integer.parseInt(table.getModel().getValueAt(row, 5).toString());

        if (stockActual <= stockMinimo) {
            if(column == 0){
                e.setIcon(new ImageIcon("img/alert.png"));
                e.setText(value.toString());
            }
        }

        return e;
    }

}

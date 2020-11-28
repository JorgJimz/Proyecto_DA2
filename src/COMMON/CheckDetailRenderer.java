package COMMON;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CheckDetailRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {

        JLabel e = new JLabel();
        e.setText(value.toString());
        e.setOpaque(true);

        JCheckBox chk = new JCheckBox();
        chk.setOpaque(true);

        if (isSelected) {
            e.setBackground(new Color(228, 1, 55));
            e.setForeground(Color.WHITE);
        }

        if (column == 0) {
            if ((Integer) value == 1) {
                chk.setSelected(true);
                return chk;
            } else {
                chk.setSelected(false);
                return chk;
            }
        } else {
            e.setText(value.toString());
            return e;
        }

    }

}

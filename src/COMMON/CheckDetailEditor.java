package COMMON;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

public class CheckDetailEditor extends AbstractCellEditor implements TableCellEditor {

    JCheckBox chk;
    JTextField txt;
    private static final int JCHECKBOX = 0;
    private static final int JTEXTFIELD = 1;

    int f;

    DefaultCellEditor[] editores;

    public CheckDetailEditor() {

        chk = new JCheckBox();
        txt = new JTextField();

        editores = new DefaultCellEditor[2];
        editores[JCHECKBOX] = new DefaultCellEditor(chk);
        editores[JTEXTFIELD] = new DefaultCellEditor(txt);

        editores[JCHECKBOX].setClickCountToStart(2);
        editores[JTEXTFIELD].setClickCountToStart(2);
    }

    public Object getCellEditorValue() {

        switch (f) {
            case JCHECKBOX:
                if (chk.isSelected()) {
                    return 1;
                } else {
                    return 0;
                }
            default:
                return txt.getText().toUpperCase();
        }

    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        switch (column) {
            case 0:
                f = JCHECKBOX;
                return editores[JCHECKBOX].getTableCellEditorComponent(table,
                        value, isSelected, row, column);
            case 5:
            case 7:
                f = JTEXTFIELD;
                return editores[JTEXTFIELD].getTableCellEditorComponent(
                        table, value, isSelected, row, column);
            default:
                return null;
        }
    }
}

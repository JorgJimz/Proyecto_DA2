package COMMON;

import DAO.ProveedorController;
import MODEL.Distrito;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.*;
import javax.swing.table.TableCellEditor;

public class ProveedorTableEditor extends AbstractCellEditor implements TableCellEditor {

    int f;

    ProveedorController controller = new ProveedorController();

    private static final int JCOMBOBOX_DISTRITO = 0;
    private static final int NORMAL = 1;

    DefaultCellEditor[] editores;

    JComboBox cboDistrito;
    JTextField txt;

    public ProveedorTableEditor() {
        cboDistrito = new JComboBox<>();
        for (Distrito d : controller.ListarDistritos()) {
            cboDistrito.addItem(d);
        }

        txt = new JTextField();

        editores = new DefaultCellEditor[2];
        editores[JCOMBOBOX_DISTRITO] = new DefaultCellEditor(cboDistrito);
        editores[NORMAL] = new DefaultCellEditor(txt);
    }

    @Override
    public Object getCellEditorValue() {
        switch (f) {
            case JCOMBOBOX_DISTRITO:
                return cboDistrito.getSelectedItem();
            default:
                return txt.getText();
        }
    }

    @Override
    public boolean stopCellEditing() {
        if (getCellEditorValue().toString().equals("")) {
            return false;
        }
        return super.stopCellEditing();
    }

    @Override
    public boolean isCellEditable(EventObject eo) {
        if (eo instanceof MouseEvent) {
            return ((MouseEvent) eo).getClickCount() >= 2;
        }
        return super.isCellEditable(eo);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (column == 6) {
            f = JCOMBOBOX_DISTRITO;
            return editores[JCOMBOBOX_DISTRITO].getTableCellEditorComponent(table,
                    value, isSelected, row, column);
        } else if (column != 6) {
            f = NORMAL;
            return editores[NORMAL].getTableCellEditorComponent(table,
                    value, isSelected, row, column);
        } else {
            return null;
        }
    }

}

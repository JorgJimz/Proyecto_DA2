package UTIL;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXSearchField;

public class Util {

    public static void Mensaje(String msg, String title, int type) {
        JOptionPane.showMessageDialog(null, "<html><h4><b>" + msg + "</b></h4></html>", title, type);
    }
    
    public static String RoundedValue(double val){
        return String.format("%.2f", val);
    }
    
    public static void Limpiar(JInternalFrame container) {
        for (Component c : container.getContentPane().getComponents()) {
            if (c instanceof JTextField && !c.getName().endsWith("_P")) {
                ((JTextField) c).setText("");
            }

            if (c instanceof JXDatePicker && !c.getName().endsWith("_P")) {
                ((JXDatePicker) c).setDate(null);
            }

            if (c instanceof JComboBox && !c.getName().endsWith("_P")) {
                ((JComboBox) c).setSelectedIndex(0);
            }
        }
    }
    
    public static void Bloquear(JInternalFrame container, boolean value) {
        for (Component c : container.getContentPane().getComponents()) {
            if (c instanceof JTextField && !c.getName().endsWith("_P") && !(c instanceof JXSearchField)) {
                if (value) {
                    ((JTextField) c).setEnabled(!value);
                } else {
                    ((JTextField) c).setEnabled(value);
                }
            }

            if (c instanceof JComboBox && !c.getName().endsWith("_P")) {
                if (value) {
                    ((JComboBox) c).setEnabled(!value);
                } else {
                    ((JComboBox) c).setEnabled(value);
                }
            }

            if (c instanceof JXDatePicker && !c.getName().endsWith("_P")) {
                if (value) {
                    ((JXDatePicker) c).setEnabled(!value);
                } else {
                    ((JXDatePicker) c).setEnabled(value);
                }
            }
        }
    }
    
     public static boolean Validar(JInternalFrame container) {
        boolean flag = true;
        for (Component c : container.getContentPane().getComponents()) {
            if (c instanceof JTextField && !(c instanceof JXSearchField)) {
                if (((JTextField) c).getText().equalsIgnoreCase("")) {
                    flag = false;
                    ((JTextField) c).setBorder(BorderFactory.createLineBorder(new Color(219, 68, 55), 3));
                } else {
                    ((JTextField) c).setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                }
            }

            if (c instanceof JXDatePicker) {
                if (((JXDatePicker) c).getDate() == null) {
                    flag = false;
                    ((JXDatePicker) c).setBorder(BorderFactory.createLineBorder(new Color(219, 68, 55), 3));
                } else {
                    ((JXDatePicker) c).setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                }
            }

            if (c instanceof JComboBox) {
                if (((JComboBox) c).getSelectedItem().toString().equalsIgnoreCase("[Seleccione]")) {
                    flag = false;
                    ((JComboBox) c).setBorder(BorderFactory.createLineBorder(new Color(219, 68, 55), 3));
                } else {
                    ((JComboBox) c).setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                }
            }
        }

        return flag;
    }
}

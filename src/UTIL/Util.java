package UTIL;

import javax.swing.JOptionPane;

public class Util {

    public static void Mensaje(String msg, String title, int type) {
        JOptionPane.showMessageDialog(null, "<html><h4><b>" + msg + "</b></h4></html>", title, type);
    }
    
    public static String RoundedValue(double val){
        return String.format("%.2f", val);
    }
}

package DAO;

import INTERFACE.IProveedor;
import MODEL.Distrito;
import MODEL.Proveedor;
import UTIL.Util;
import UTIL.dbBean;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProveedorController implements IProveedor {

    @Override
    public void EliminarProveedor(int id) {
        try {
            dbBean db = new dbBean();
            db.insertSQL("DELETE FROM PROVEEDOR WHERE ID = ?", new Object[]{
                id
            });
            Util.Mensaje("¡Proveedor Eliminado!", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ListarProveedor(JTable tabla, DefaultTableModel modelo, String criteria) {
        try {
            dbBean db = new dbBean();
            ResultSet r = db.execParamSQL("SELECT P.*, D.NOMBRE AS DISTRITO_NOMBRE FROM PROVEEDOR P INNER JOIN DISTRITO D ON P.DISTRITO_ID = D.ID WHERE (? = '' OR RAZON_SOCIAL LIKE ?)", new String[]{criteria + "%", criteria + "%"});
            modelo.setRowCount(0);
            while (r.next()) {
                modelo.addRow(new Object[]{
                    r.getInt("ID"),
                    r.getString("RAZON_SOCIAL"),
                    r.getString("DIRECCION"),
                    r.getString("TELEFONO"),
                    r.getString("EMAIL"),
                    r.getString("RUC"),
                    r.getString("DISTRITO_NOMBRE").trim(),
                    r.getInt("DIAS_PAGO"),
                    r.getString("CTA_BCP"),
                    r.getString("CONTACTO")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tabla.setModel(modelo);
    }

    @Override
    public void ActualizarProveedor(Proveedor objProveedor) {
        try {
            dbBean db = new dbBean();
            db.insertSQL("UPDATE PROVEEDOR SET RAZON_SOCIAL = ?, DIRECCION = ?, TELEFONO = ?, EMAIL = ?, RUC = ?, DISTRITO_ID = ?, DIAS_PAGO = ?, CTA_BCP = ?, CONTACTO = ?  WHERE ID = ?", new Object[]{
                objProveedor.getRAZON_SOCIAL(),
                objProveedor.getDIRECCION(),
                objProveedor.getTELEFONO(),
                objProveedor.getEMAIL(),
                objProveedor.getRUC(),
                objProveedor.getDISTRITO_ID(),
                objProveedor.getDIAS_PAGO(),
                objProveedor.getCTA_BCP(),
                objProveedor.getCONTACTO(),
                objProveedor.getID()
            });
            Util.Mensaje("¡Proveedor Actualizado!", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void GrabarProveedor(Proveedor objProveedor) {
        try {
            dbBean db = new dbBean();
            db.insertSQL("INSERT INTO PROVEEDOR VALUES (?,?,?,?,?,?,?,?,?)", new Object[]{
                objProveedor.getRAZON_SOCIAL(),
                objProveedor.getDIRECCION(),
                objProveedor.getTELEFONO(),
                objProveedor.getEMAIL(),
                objProveedor.getRUC(),
                objProveedor.getDISTRITO().getID(),
                objProveedor.getDIAS_PAGO(),
                objProveedor.getCTA_BCP(),
                objProveedor.getCONTACTO()
            });

            Util.Mensaje("¡Proveedor Guardado!", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Distrito> ListarDistritos() {
        ArrayList<Distrito> arrDis = new ArrayList<Distrito>();
        try {
            dbBean db = new dbBean();
            ResultSet r = db.execSQL("SELECT * FROM DISTRITO");
            arrDis.add(new Distrito(0, "[Seleccione]"));
            while (r.next()) {
                Distrito d = new Distrito();
                d.setID(r.getInt("ID"));
                d.setNOMBRE(r.getString("NOMBRE").trim());
                arrDis.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrDis;
    }

}

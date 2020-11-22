package DAO;

import INTERFACE.IPedido;
import MODEL.Detalle_Orden;
import MODEL.Distrito;
import MODEL.Estado;
import MODEL.Orden;
import MODEL.Producto;
import MODEL.Proveedor;
import UI.Principal;
import UTIL.Util;
import UTIL.dbBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import javax.swing.JOptionPane;

public class PedidoController implements IPedido {

    @Override
    public Orden ObtenerOrden(String id) {
        Orden o = null;
        ArrayList<Detalle_Orden> l = new ArrayList<Detalle_Orden>();
        try {
            dbBean cnn = new dbBean();
            ResultSet rs = cnn.execParamSQL("SELECT O.*, E.ID ID_ESTADO, E.DESCRIPCION DESC_ESTADO FROM ORDEN O INNER JOIN ESTADO E ON O.ESTADO_ID = E.ID WHERE O.ID = ?", new String[]{id});
            while (rs.next()) {
                o = new Orden();
                o.setID(rs.getInt("ID"));
                o.setFECHA(rs.getString("FECHA"));
                o.setESTADO(new Estado(rs.getInt("ID_ESTADO"), rs.getString("DESC_ESTADO")));
            }
            if (o != null) {
                ResultSet rsDet = cnn.execParamSQL("SELECT DO.CANTIDAD, P.ID, P.DESCRIPCION, P.PRECIO FROM DETALLE_ORDEN DO INNER JOIN PRODUCTO P ON DO.PRODUCTO_ID = P.ID WHERE DO.ORDEN_ID = ?", new String[]{id});
                while (rsDet.next()) {
                    Detalle_Orden d = new Detalle_Orden();
                    d.setCANTIDAD(rsDet.getInt("CANTIDAD"));
                    d.setPRODUCTO(new Producto(rsDet.getInt("ID"), rsDet.getString("DESCRIPCION"), rsDet.getDouble("PRECIO")));
                    l.add(d);
                }
                o.setDETALLE(l);
            }
        } catch (SQLException e) {
            Util.Mensaje(e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return o;
    }

    @Override
    public void GenerarPedido(ArrayList<Detalle_Orden> arrDetalle, String obs) {
        try {
            dbBean cnn = new dbBean();
            int r2 = 0;
            int r = cnn.insertSQL("INSERT INTO ORDEN VALUES (GETDATE(), 1, NULL)", null);
            if (r > 0) {
                for (Detalle_Orden item : arrDetalle) {
                    r2 = cnn.insertSQL("INSERT INTO DETALLE_ORDEN VALUES (?,?,?)", new Object[]{r, item.getPRODUCTO_ID(), item.getCANTIDAD()});
                }
                if (r2 >= 0) {
                    Util.Mensaje(String.format("¡Orden %s generada con éxito!", String.format("%05d", r)), "Exito", JOptionPane.INFORMATION_MESSAGE);
                    GenerarHistorial(r, 1, Principal.USUARIO.getCODIGO(), obs);
                }
            }
        } catch (SQLException e) {
            Util.Mensaje(e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public ArrayList<Proveedor> ObtenerProveedores() {
        ArrayList<Proveedor> l = new ArrayList<Proveedor>();
        try {
            dbBean cnn = new dbBean();
            ResultSet rs = cnn.execSQL("SELECT P.*, D.ID ID_DIST, D.NOMBRE NOM_DIST FROM PROVEEDOR P INNER JOIN DISTRITO D ON P.DISTRITO_ID = D.ID");
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setID(rs.getInt("ID"));
                p.setRAZON_SOCIAL(rs.getString("RAZON_SOCIAL"));
                p.setDIRECCION(rs.getString("DIRECCION"));
                p.setTELEFONO(rs.getString("TELEFONO"));
                p.setEMAIL(rs.getString("EMAIL"));
                p.setRUC(rs.getString("RUC"));
                p.setDISTRITO(new Distrito(rs.getInt("ID_DIST"), rs.getString("NOM_DIST")));
                l.add(p);
            }

        } catch (SQLException e) {
            Util.Mensaje(e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return l;
    }

    @Override
    public ArrayList<Producto> CargarProductos(String criteria) {
        ArrayList<Producto> lp = new ArrayList<Producto>();
        try {
            dbBean cnn = new dbBean();
            ResultSet rs = cnn.execParamSQL("SELECT * FROM PRODUCTO WHERE DESCRIPCION LIKE ?", new String[]{criteria + "%"});

            while (rs.next()) {
                Producto p = new Producto();
                p.setID(rs.getInt("ID"));
                p.setDESCRIPCION(rs.getString("DESCRIPCION"));
                p.setPRECIO(rs.getDouble("PRECIO"));
                p.setUNIDAD_MEDIDA(rs.getString("UNIDAD_MEDIDA"));
                p.setSTOCK_ACTUAL(rs.getInt("STOCK_ACTUAL"));
                p.setSTOCK_MINIMO(rs.getInt("STOCK_MINIMO"));
                lp.add(p);
            }
        } catch (SQLException e) {
            Util.Mensaje(e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return lp;
    }

    @Override
    public void GenerarHistorial(int o, int e, String usr, String obs) {
        try {
            dbBean cnn = new dbBean();
            int r = cnn.insertSQL("INSERT INTO HISTORIAL VALUES (?, ?, GETDATE(), ?, ?)", new Object[]{e, o, usr, obs});
        } catch (SQLException ex) {
            Util.Mensaje(ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void ProcesarOrden(Orden o) {
        try {
            dbBean cnn = new dbBean();
            int r = cnn.insertSQL("UPDATE ORDEN SET PROVEEDOR_ID = ?, ESTADO_ID = ?, FACTURA = ? WHERE ID = ?", new Object[]{ o.getPROVEEDOR().getID(), o.getESTADO().getID(), o.getFACTURA(), o.getID()});
            GenerarHistorial(o.getID(), o.getESTADO().getID(), Principal.USUARIO.getCODIGO(), o.getOBS());
            Util.Mensaje("Orden " + String.format("%05d", o.getID()) + " procesada.", "Exito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Util.Mensaje(ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public ArrayList<Orden> ObtenerOrdenes(int estado) {
        ArrayList<Orden> l = new ArrayList<Orden>();
        try {
            dbBean cnn = new dbBean();
            ResultSet rs = cnn.execParamSQL("SELECT O.ID, O.FECHA, O.PROVEEDOR_ID, P.RAZON_SOCIAL, E.DESCRIPCION, H.USUARIO FROM ORDEN O INNER JOIN ESTADO E ON O.ESTADO_ID = E.ID INNER JOIN HISTORIAL H ON H.ESTADO_ID = E.ID AND H.ORDEN_ID = O.ID INNER JOIN PROVEEDOR P ON O.PROVEEDOR_ID = P.ID WHERE E.ID = ?", new String[]{String.format("%d", estado)});
            while (rs.next()) {
                Orden p = new Orden();
                p.setID(rs.getInt("ID"));
                Estado e = new Estado();
                e.setDESCRIPCION(rs.getString("DESCRIPCION"));
                p.setESTADO(e);
                Proveedor v = new Proveedor();
                v.setID(rs.getInt("PROVEEDOR_ID"));
                v.setRAZON_SOCIAL(rs.getString("RAZON_SOCIAL"));
                p.setPROVEEDOR(v);
                p.setFECHA(rs.getString("FECHA"));
                p.setUSUARIO(rs.getString("USUARIO"));
                l.add(p);
            }

        } catch (SQLException e) {
            Util.Mensaje(e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return l;
    }

}

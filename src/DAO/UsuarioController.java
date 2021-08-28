package DAO;

import INTERFACE.IUsuario;
import MODEL.Aplicacion;
import MODEL.Modulo;
import MODEL.Perfil;
import MODEL.Usuario;
import UTIL.dbBean;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioController implements IUsuario {

    @Override
    public Usuario IniciarSesion(String usr, String pwd) {
        Usuario u = null;
        try {
            dbBean cnn = new dbBean();
            ResultSet rs = cnn.execParamSQL("SELECT U.*, P.ID AS PERFIL_ID, P.DESCRIPCION AS PERFIL_DESC FROM USUARIO U INNER JOIN PERFIL P ON U.PERFIL_ID = P.ID WHERE U.CODIGO = ? AND U.PASSWORD = ?", new String[]{usr, pwd});
            while (rs.next()) {
                u = new Usuario(rs.getInt("ID"), rs.getString("CODIGO"), rs.getString("PERSONA"), new Perfil(rs.getInt("PERFIL_ID"), rs.getString("PERFIL_DESC")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public ArrayList<Aplicacion> ObtenerMenu(String perfilId) {
        ArrayList<Aplicacion> arr = new ArrayList<Aplicacion>();
        try {
            dbBean cnn = new dbBean();
            ResultSet rs = cnn.execParamSQL("SELECT M.*, A.ID AS APP_ID, A.DESCRIPCION AS APP_DESC, A.CLASE AS APP_CLASS, MD.ID AS MD_ID, MD.DESCRIPCION AS MD_DESC FROM MENU M INNER JOIN APLICACION A ON M.APLICACION_ID = A.ID INNER JOIN MODULO MD ON A.MODULO_ID = MD.ID WHERE M.PERFIL_ID = ?", new String[]{perfilId});
            while (rs.next()) {
                Aplicacion a = new Aplicacion();
                a.setID(rs.getInt("APP_ID"));
                a.setDESCRIPCION(rs.getString("APP_DESC"));
                a.setCLASE(rs.getString("APP_CLASS"));
                Modulo m = new Modulo();
                m.setID(rs.getInt("MD_ID"));
                a.setMODULO_ID(rs.getInt("MD_ID"));
                m.setDESCRIPCION(rs.getString("MD_DESC"));
                a.setMODULO(m);
                arr.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

}

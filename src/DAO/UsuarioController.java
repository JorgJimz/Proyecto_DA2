package DAO;

import INTERFACE.IUsuario;
import MODEL.Perfil;
import MODEL.Usuario;
import UTIL.dbBean;
import java.sql.ResultSet;

public class UsuarioController implements IUsuario {

    @Override
    public Usuario IniciarSesion(String usr, String pwd) {
        Usuario u = null;
        try {
            dbBean cnn = new dbBean();
            ResultSet rs = cnn.execParamSQL("SELECT * FROM USUARIO U INNER JOIN PERFIL P ON U.PERFIL_ID = P.ID WHERE U.CODIGO = ? AND U.PASSWORD = ?", new String[]{usr, pwd});
            while (rs.next()) {
                u = new Usuario(rs.getInt("ID"), rs.getString("CODIGO"), rs.getString("PERSONA"), new Perfil(rs.getInt("ID"),rs.getString("DESCRIPCION")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

}

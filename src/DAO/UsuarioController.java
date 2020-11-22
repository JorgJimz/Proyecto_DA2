package DAO;

import INTERFACE.IUsuario;
import MODEL.Usuario;
import UTIL.dbBean;
import java.sql.ResultSet;

public class UsuarioController implements IUsuario {

    @Override
    public Usuario IniciarSesion(String usr, String pwd) {
        Usuario u = null;
        try {
            dbBean cnn = new dbBean();
            ResultSet rs = cnn.execParamSQL("SELECT * FROM USUARIO WHERE CODIGO = ? AND PASSWORD = ?", new String[]{usr, pwd});
            while (rs.next()) {
                u = new Usuario(rs.getInt("ID"), rs.getString("CODIGO"), rs.getString("PERSONA"), rs.getString("PERFIL"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

}

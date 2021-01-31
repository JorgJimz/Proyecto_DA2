package INTERFACE;

import MODEL.Aplicacion;
import MODEL.Usuario;
import java.util.ArrayList;

public interface IUsuario {
     public Usuario IniciarSesion(String usr, String pwd);
     public ArrayList<Aplicacion> ObtenerMenu(String perfilId);
}

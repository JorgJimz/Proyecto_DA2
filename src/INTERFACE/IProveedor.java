package INTERFACE;

import MODEL.Distrito;
import MODEL.Proveedor;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public interface IProveedor {
    public void EliminarProveedor(int id);
    public void ListarProveedor(JTable tabla, DefaultTableModel modelo, String criteria);
    public void ActualizarProveedor(Proveedor objLibro);
    public void GrabarProveedor(Proveedor objLibro);
    public ArrayList<Distrito> ListarDistritos();
}

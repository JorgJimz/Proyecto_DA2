package INTERFACE;

import MODEL.Detalle_Orden;
import MODEL.Orden;
import MODEL.Producto;
import MODEL.Proveedor;
import java.util.ArrayList;

public interface IPedido {
    public Orden ObtenerOrden(String id);
    public ArrayList<Orden> ObtenerOrdenes(int estado);
    public ArrayList<Proveedor> ObtenerProveedores();
    public void GenerarPedido(ArrayList<Detalle_Orden> arrDetalle, String obs);
    public ArrayList<Producto> CargarProductos(String criteria);
    public void GenerarHistorial(int o, int e, String usr, String obs);
    public void ProcesarOrden(Orden o);
}

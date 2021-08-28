package INTERFACE;

import MODEL.Detalle_Orden;
import MODEL.Historial;
import MODEL.Kardex;
import MODEL.Orden;
import MODEL.Producto;
import MODEL.Proveedor;
import java.util.ArrayList;

public interface IPedido {
    public Orden ObtenerOrden(String id);
    public ArrayList<Orden> ObtenerOrdenes(int estado, String id);
    public ArrayList<Proveedor> ObtenerProveedores();
    public Orden GenerarPedido(ArrayList<Detalle_Orden> arrDetalle, String obs);
    public ArrayList<Producto> CargarProductos(String criteria, boolean filtro);
    public void GenerarHistorial(int o, int e, String usr, String obs);
    public void ProcesarOrden(Orden o);
    public void GenerarKardex(ArrayList<Kardex> arr);
    public boolean ValidarFactura(String pId, String factura);
    public ArrayList<Historial> ObtenerHistorial(int id);
}

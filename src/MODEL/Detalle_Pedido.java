
package MODEL;

public class Detalle_Pedido {

    private int ID;
    private String DESCRIPCION;
    private double PRECIO;
    private int CANTIDAD;
    private double IMPORTE;

    public Detalle_Pedido() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public double getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(double PRECIO) {
        this.PRECIO = PRECIO;
    }

    public int getCANTIDAD() {
        return CANTIDAD;
    }

    public void setCANTIDAD(int CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }

    public double getIMPORTE() {
        return IMPORTE;
    }

    public void setIMPORTE(double IMPORTE) {
        this.IMPORTE = IMPORTE;
    }


    

    

}

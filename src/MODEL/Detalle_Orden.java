package MODEL;

public class Detalle_Orden {

    private int ORDEN_ID;
    private Orden ORDEN;
    private int PRODUCTO_ID;
    private Producto PRODUCTO;
    private int CANTIDAD;

    //NOT MAPPED
    private double TOTAL;
    private double PRECIO;
    private String NOMBRE;

    public Detalle_Orden() {
    }

    public Orden getORDEN() {
        return ORDEN;
    }

    public Producto getPRODUCTO() {
        return PRODUCTO;
    }

    public int getCANTIDAD() {
        return CANTIDAD;
    }

    public void setORDEN(Orden ORDEN) {
        this.ORDEN = ORDEN;
    }

    public void setPRODUCTO(Producto PRODUCTO) {
        this.PRODUCTO = PRODUCTO;
    }

    public void setCANTIDAD(int CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }

    public int getORDEN_ID() {
        return ORDEN_ID;
    }

    public int getPRODUCTO_ID() {
        return PRODUCTO_ID;
    }

    public void setORDEN_ID(int ORDEN_ID) {
        this.ORDEN_ID = ORDEN_ID;
    }

    public void setPRODUCTO_ID(int PRODUCTO_ID) {
        this.PRODUCTO_ID = PRODUCTO_ID;
    }

    public double getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(double TOTAL) {
        this.TOTAL = TOTAL;
    }

    public double getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(double PRECIO) {
        this.PRECIO = PRECIO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

}

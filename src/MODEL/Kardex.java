package MODEL;

public class Kardex {

    private int ID;
    private int PRODUCTO_ID;
    private int CANTIDAD_RECIBIDA;
    private int CANTIDAD_PEDIDA;
    private String MOVIMIENTO;
    private int STOCK_ANTERIOR;
    private int NUEVO_STOCK;
    private String FECHA;
    private String OBSERVACION;
    private int ORDEN_ID;

    public Kardex() {
    }

    public Kardex(int PRODUCTO_ID, int CANTIDAD_RECIBIDA, int CANTIDAD_PEDIDA, String MOVIMIENTO, int STOCK_ANTERIOR, int NUEVO_STOCK, String OBSERVACION, int ORDEN_ID) {

        this.PRODUCTO_ID = PRODUCTO_ID;
        this.CANTIDAD_RECIBIDA = CANTIDAD_RECIBIDA;
        this.CANTIDAD_PEDIDA = CANTIDAD_PEDIDA;
        this.MOVIMIENTO = MOVIMIENTO;
        this.STOCK_ANTERIOR = STOCK_ANTERIOR;
        this.NUEVO_STOCK = NUEVO_STOCK;
        this.OBSERVACION = OBSERVACION;
        this.ORDEN_ID = ORDEN_ID;
    }

    public int getID() {
        return ID;
    }

    public int getPRODUCTO_ID() {
        return PRODUCTO_ID;
    }

    public int getCANTIDAD_RECIBIDA() {
        return CANTIDAD_RECIBIDA;
    }

    public int getCANTIDAD_PEDIDA() {
        return CANTIDAD_PEDIDA;
    }

    public String getMOVIMIENTO() {
        return MOVIMIENTO;
    }

    public int getSTOCK_ANTERIOR() {
        return STOCK_ANTERIOR;
    }

    public int getNUEVO_STOCK() {
        return NUEVO_STOCK;
    }

    public String getFECHA() {
        return FECHA;
    }

    public String getOBSERVACION() {
        return OBSERVACION;
    }

    public int getORDEN_ID() {
        return ORDEN_ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setPRODUCTO_ID(int PRODUCTO_ID) {
        this.PRODUCTO_ID = PRODUCTO_ID;
    }

    public void setCANTIDAD_RECIBIDA(int CANTIDAD_RECIBIDA) {
        this.CANTIDAD_RECIBIDA = CANTIDAD_RECIBIDA;
    }

    public void setCANTIDAD_PEDIDA(int CANTIDAD_PEDIDA) {
        this.CANTIDAD_PEDIDA = CANTIDAD_PEDIDA;
    }

    public void setMOVIMIENTO(String MOVIMIENTO) {
        this.MOVIMIENTO = MOVIMIENTO;
    }

    public void setSTOCK_ANTERIOR(int STOCK_ANTERIOR) {
        this.STOCK_ANTERIOR = STOCK_ANTERIOR;
    }

    public void setNUEVO_STOCK(int NUEVO_STOCK) {
        this.NUEVO_STOCK = NUEVO_STOCK;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public void setOBSERVACION(String OBSERVACION) {
        this.OBSERVACION = OBSERVACION;
    }

    public void setORDEN_ID(int ORDEN_ID) {
        this.ORDEN_ID = ORDEN_ID;
    }

}

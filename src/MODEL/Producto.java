package MODEL;

public class Producto {

    private int ID;
    private String DESCRIPCION;
    private double PRECIO;
    private String UNIDAD_MEDIDA;
    private int STOCK_ACTUAL;
    private int STOCK_MINIMO;

    public Producto() {
    }

    public Producto(int ID, String DESCRIPCION, double PRECIO) {
        this.ID = ID;
        this.DESCRIPCION = DESCRIPCION;
        this.PRECIO = PRECIO;
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

    public String getUNIDAD_MEDIDA() {
        return UNIDAD_MEDIDA;
    }

    // procedimientoe
    public void setUNIDAD_MEDIDA(String UNIDAD_MEDIDA) {
        this.UNIDAD_MEDIDA = UNIDAD_MEDIDA;
    }

    public int getSTOCK_ACTUAL() {
        return STOCK_ACTUAL;
    }

    //procedimiento
    public void setSTOCK_ACTUAL(int STOCK_ACTUAL) {
        this.STOCK_ACTUAL = STOCK_ACTUAL;
    }

    public int getSTOCK_MINIMO() {
        return STOCK_MINIMO;
    }

    //procedimiento
    public void setSTOCK_MINIMO(int STOCK_MINIMO) {
        this.STOCK_MINIMO = STOCK_MINIMO;
    }

}

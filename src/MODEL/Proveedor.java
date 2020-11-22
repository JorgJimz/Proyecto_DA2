package MODEL;

public class Proveedor {

    private int ID;
    private String RAZON_SOCIAL;
    private String DIRECCION;
    private String TELEFONO;
    private String EMAIL;
    private String RUC;
    private Distrito DISTRITO;

    public Proveedor() {
    }

    public int getID() {
        return ID;
    }

    public String getRAZON_SOCIAL() {
        return RAZON_SOCIAL;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public String getRUC() {
        return RUC;
    }

    public Distrito getDISTRITO() {
        return DISTRITO;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setRAZON_SOCIAL(String RAZON_SOCIAL) {
        this.RAZON_SOCIAL = RAZON_SOCIAL;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public void setDISTRITO(Distrito DISTRITO) {
        this.DISTRITO = DISTRITO;
    }

    @Override
    public String toString() {
        return RAZON_SOCIAL;
    }

}

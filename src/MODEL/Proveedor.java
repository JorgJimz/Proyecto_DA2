package MODEL;

public class Proveedor {

    private int ID;
    private String RAZON_SOCIAL;
    private String DIRECCION;
    private String TELEFONO;
    private String EMAIL;
    private String RUC;
    private int DISTRITO_ID;
    private Distrito DISTRITO;
    private int DIAS_PAGO;
    private String CONTACTO;
    private String CTA_BCP;

    public Proveedor() {
    }

    public Proveedor(int ID, String RAZON_SOCIAL) {
        this.ID = ID;
        this.RAZON_SOCIAL = RAZON_SOCIAL;
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

    public int getDIAS_PAGO() {
        return DIAS_PAGO;
    }

    public String getCONTACTO() {
        return CONTACTO;
    }

    public String getCTA_BCP() {
        return CTA_BCP;
    }

    public int getDISTRITO_ID() {
        return DISTRITO_ID;
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

    public void setDIAS_PAGO(int DIAS_PAGO) {
        this.DIAS_PAGO = DIAS_PAGO;
    }

    public void setCONTACTO(String CONTACTO) {
        this.CONTACTO = CONTACTO;
    }

    public void setCTA_BCP(String CTA_BCP) {
        this.CTA_BCP = CTA_BCP;
    }

    public void setDISTRITO_ID(int DISTRITO_ID) {
        this.DISTRITO_ID = DISTRITO_ID;
    }

    @Override
    public String toString() {
        return RAZON_SOCIAL;
    }

}

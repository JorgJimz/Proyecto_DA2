package MODEL;

public class Historial {

    private int ID;
    private int ESTADO_ID;
    private Estado ESTADO;
    private int ORDEN_ID;
    private String FECHA;
    private String USUARIO;
    private String OBSERVACION;

    public Historial(int ID, int ESTADO_ID, int ORDEN_ID, String FECHA, String USUARIO, String OBSERVACION) {
        this.ID = ID;
        this.ESTADO_ID = ESTADO_ID;
        this.ORDEN_ID = ORDEN_ID;
        this.FECHA = FECHA;
        this.USUARIO = USUARIO;
        this.OBSERVACION = OBSERVACION;
    }

    public Historial() {

    }

    public int getID() {
        return ID;
    }

    public int getESTADO_ID() {
        return ESTADO_ID;
    }

    public int getORDEN_ID() {
        return ORDEN_ID;
    }

    public String getFECHA() {
        return FECHA;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public String getOBSERVACION() {
        return OBSERVACION;
    }

    public Estado getESTADO() {
        return ESTADO;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setESTADO_ID(int ESTADO_ID) {
        this.ESTADO_ID = ESTADO_ID;
    }

    public void setORDEN_ID(int ORDEN_ID) {
        this.ORDEN_ID = ORDEN_ID;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public void setOBSERVACION(String OBSERVACION) {
        this.OBSERVACION = OBSERVACION;
    }

    public void setESTADO(Estado ESTADO) {
        this.ESTADO = ESTADO;
    }

}

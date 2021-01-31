package MODEL;

public class Aplicacion {

    private int ID;
    private String DESCRIPCION;
    private int MODULO_ID;
    private String CLASE;

    private Modulo MODULO;

    public Aplicacion() {
    }

    public int getID() {
        return ID;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public int getMODULO_ID() {
        return MODULO_ID;
    }

    public Modulo getMODULO() {
        return MODULO;
    }

    public String getCLASE() {
        return CLASE;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public void setMODULO_ID(int MODULO_ID) {
        this.MODULO_ID = MODULO_ID;
    }

    public void setMODULO(Modulo MODULO) {
        this.MODULO = MODULO;
    }

    public void setCLASE(String CLASE) {
        this.CLASE = CLASE;
    }

}

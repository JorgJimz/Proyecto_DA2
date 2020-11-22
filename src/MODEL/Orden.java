package MODEL;

import java.util.ArrayList;

public class Orden {

    private int ID;
    private String FECHA;
    private Estado ESTADO;
    private Proveedor PROVEEDOR;
    private String FACTURA;

    //NOT MAPPED
    private String OBS;
    private String USUARIO;

    private ArrayList<Detalle_Orden> DETALLE;

    public Orden() {
        DETALLE = new ArrayList<Detalle_Orden>();
    }

    public int getID() {
        return ID;
    }

    public String getFECHA() {
        return FECHA;
    }

    public Estado getESTADO() {
        return ESTADO;
    }

    public Proveedor getPROVEEDOR() {
        return PROVEEDOR;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public void setESTADO(Estado ESTADO) {
        this.ESTADO = ESTADO;
    }

    public void setPROVEEDOR(Proveedor PROVEEDOR) {
        this.PROVEEDOR = PROVEEDOR;
    }

    public String getFACTURA() {
        return FACTURA;
    }

    public void setFACTURA(String FACTURA) {
        this.FACTURA = FACTURA;
    }
    
    public ArrayList<Detalle_Orden> getDETALLE() {
        return DETALLE;
    }

    public void setDETALLE(ArrayList<Detalle_Orden> DETALLE) {
        this.DETALLE = DETALLE;
    }    
    
    public String getOBS() {
        return OBS;
    }

    public void setOBS(String OBS) {
        this.OBS = OBS;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }    
}

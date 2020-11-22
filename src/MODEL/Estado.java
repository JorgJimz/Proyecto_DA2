package MODEL;

public class Estado {
    
    public static final int GENERADA = 1;
    public static final int PROCESADA = 2;
    public static final int ANULADA = 3;
    public static final int ENTREGADA = 4;

    private int ID;
    private String DESCRIPCION;

    public Estado() {
    }

    public Estado(int ID) {
        this.ID = ID;
    }
    
    public Estado(int ID, String DESCRIPCION) {
        this.ID = ID;
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getID() {
        return ID;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

}

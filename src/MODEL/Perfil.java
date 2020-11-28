package MODEL;

public class Perfil {

    private int ID;
    private String DESCRIPCION;

    public Perfil(int ID, String DESCRIPCION) {
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

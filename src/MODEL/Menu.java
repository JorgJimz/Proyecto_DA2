package MODEL;

public class Menu {

    private int ID;
    private int PERFIL_ID;
    private int APLICACION_ID;

    public Menu() {
    }

    public int getID() {
        return ID;
    }

    public int getPERFIL_ID() {
        return PERFIL_ID;
    }

    public int getAPLICACION_ID() {
        return APLICACION_ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setPERFIL_ID(int PERFIL_ID) {
        this.PERFIL_ID = PERFIL_ID;
    }

    public void setAPLICACION_ID(int APLICACION_ID) {
        this.APLICACION_ID = APLICACION_ID;
    }
}

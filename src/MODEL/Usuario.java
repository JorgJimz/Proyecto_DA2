package MODEL;

public class Usuario {

    private int ID;
    private String CODIGO;
    private String PERSONA;
    private String PASSWORD;
    private String PERFIL;

    public Usuario(int ID, String CODIGO, String PERSONA, String PERFIL) {
        this.ID = ID;
        this.CODIGO = CODIGO;
        this.PERSONA = PERSONA;
        this.PERFIL = PERFIL;
    }

    public int getID() {
        return ID;
    }

    public String getCODIGO() {
        return CODIGO;
    }

    public String getPERSONA() {
        return PERSONA;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getPERFIL() {
        return PERFIL;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCODIGO(String CODIGO) {
        this.CODIGO = CODIGO;
    }

    public void setPERSONA(String PERSONA) {
        this.PERSONA = PERSONA;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public void setPERFIL(String PERFIL) {
        this.PERFIL = PERFIL;
    }

}

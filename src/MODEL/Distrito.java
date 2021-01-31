package MODEL;

public class Distrito {

    private int ID;
    private String NOMBRE;

    public Distrito() {
    }

    public Distrito(int ID, String NOMBRE) {
        this.ID = ID;
        this.NOMBRE = NOMBRE;
    }
        
    public int getID() {
        return ID;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    @Override
    public String toString() {
        return NOMBRE.trim();
    }

    
    
}

package Clases;

public class Operacion {

    private int id_Operacion;
    private String Operacion;

    public int getId_Operacion() {
        return id_Operacion;
    }

    public void setId_Operacion(int id_Operacion) {
        this.id_Operacion = id_Operacion;
    }

    public String getOperacion() {
        return Operacion;
    }

    public void setOperacion(String Operacion) {
        this.Operacion = Operacion;
    }

    public Operacion(int id_Operacion, String Operacion) {
        this.id_Operacion = id_Operacion;
        this.Operacion = Operacion;
    }

    public Operacion() {
    }

    @Override
    public String toString() {
        return Operacion;
    }
}

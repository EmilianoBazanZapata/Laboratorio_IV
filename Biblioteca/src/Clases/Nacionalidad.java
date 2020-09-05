package Clases;

public class Nacionalidad {

    public Nacionalidad() {
    }

    private int idNacionalidad;
    private String Nacionalidad;
    public int getIdNacionalidad() {
        return idNacionalidad;
    }

    public void setIdNacionalidad(int idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String Nacionalidad) {
        this.Nacionalidad = Nacionalidad;
    }

    public Nacionalidad(int idNacionalidad, String Nacionalidad) {
        this.idNacionalidad = idNacionalidad;
        this.Nacionalidad = Nacionalidad;
    }

    @Override
    public String toString() {
        return Nacionalidad;
    }

    
}

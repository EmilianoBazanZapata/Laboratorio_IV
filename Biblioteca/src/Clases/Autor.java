package Clases;

public class Autor {

    public Autor() {
    }

    private int Dni;
    private String Nombre;
    private Nacionalidad nacionalidad;

    public int getDni() {
        return Dni;
    }

    public void setDni(int Dni) {
        this.Dni = Dni;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Nacionalidad getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidad nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Autor(int Dni, String Nombre, Nacionalidad nacionalidad) {
        this.Dni = Dni;
        this.Nombre = Nombre;
        this.nacionalidad = nacionalidad;
    }
}

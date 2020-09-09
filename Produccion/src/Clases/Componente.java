package Clases;

public class Componente {

    private int idComponente;
    private String descripcion;

    public int getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(int idComponente) {
        this.idComponente = idComponente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Componente() {
    }

    public Componente(int idComponente, String descripcion) {
        this.idComponente = idComponente;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }

}

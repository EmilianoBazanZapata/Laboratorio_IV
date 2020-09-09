package Clases;

public class Responsable {

    private int idResponsable;
    private String NombreResponssable;

    public int getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(int idResponsable) {
        this.idResponsable = idResponsable;
    }

    public String getNombreResponssable() {
        return NombreResponssable;
    }

    public void setNombreResponssable(String NombreResponssable) {
        this.NombreResponssable = NombreResponssable;
    }

    public Responsable() {
    }

    public Responsable(int idResponsable, String NombreResponssable) {
        this.idResponsable = idResponsable;
        this.NombreResponssable = NombreResponssable;
    }

}

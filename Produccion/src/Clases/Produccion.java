package Clases;

public class Produccion {

    private int idProduccion;
    private String Fecha;
    private int cantidad;
    private Responsable responsable;
    private Componente componente;

    public int getIdProduccion() {
        return idProduccion;
    }

    public void setIdProduccion(int idProduccion) {
        this.idProduccion = idProduccion;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public Produccion() {
    }
    public Produccion(int cantidad, Responsable responsable, Componente componente,String Fecha) {
        this.Fecha = Fecha;
        this.cantidad = cantidad;
        this.responsable = responsable;
        this.componente = componente;
    }

}

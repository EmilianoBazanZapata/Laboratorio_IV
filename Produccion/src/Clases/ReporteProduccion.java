package Clases;

public class ReporteProduccion {

    private int id;
    private int cantidad;
    private String fecha;
    private String Responsable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getResponsable() {
        return Responsable;
    }

    public void setResponsable(String Responsable) {
        this.Responsable = Responsable;
    }

    public ReporteProduccion(int id, int cantidad, String fecha, String Responsable) {
        this.id = id;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.Responsable = Responsable;
    }

}

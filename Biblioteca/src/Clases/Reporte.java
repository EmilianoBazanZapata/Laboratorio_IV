package Clases;

public class Reporte {

    private int totaldelibros;
    private float porcentaje_de_libros;

    public int getTotaldelibros() {
        return totaldelibros;
    }

    public void setTotaldelibros(int totaldelibros) {
        this.totaldelibros = totaldelibros;
    }

    public float getPorcentaje_de_libros() {
        return porcentaje_de_libros;
    }

    public void setPorcentaje_de_libros(float porcentaje_de_libros) {
        this.porcentaje_de_libros = porcentaje_de_libros;
    }

    public Reporte(int totaldelibros, float porcentaje_de_libros) {
        this.totaldelibros = totaldelibros;
        this.porcentaje_de_libros = porcentaje_de_libros;
    }

}

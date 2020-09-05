package Clases;

public class Libro {

    private int Codigo;
    private String Descripcion;
    private Prestamo prestamo;
    private Autor autor;

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Libro(int Codigo, String Descripcion, Prestamo prestamo, Autor autor) {
        this.Codigo = Codigo;
        this.Descripcion = Descripcion;
        this.prestamo = prestamo;
        this.autor = autor;
    }


}

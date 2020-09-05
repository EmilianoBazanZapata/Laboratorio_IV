package Clases;

public class Prestamo {

    private int IdPrestamo;
    private String Prestamo;

    public int getIdPrestamo() {
        return IdPrestamo;
    }

    public void setIdPrestamo(int IdPrestamo) {
        this.IdPrestamo = IdPrestamo;
    }

    public String getPrestamo() {
        return Prestamo;
    }

    public void setPrestamo(String Prestamo) {
        this.Prestamo = Prestamo;
    }

    public Prestamo(int IdPrestamo, String Prestamo) {
        this.IdPrestamo = IdPrestamo;
        this.Prestamo = Prestamo;
    }

    public Prestamo() {
    }

    @Override
    public String toString() {
        return Prestamo;
    }

}

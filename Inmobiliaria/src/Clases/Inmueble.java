package Clases;

public class Inmueble {

    private int id_Inmuenle;
    private String Inmueble;

    public int getId_Inmuenle() {
        return id_Inmuenle;
    }

    public void setId_Inmuenle(int id_Inmuenle) {
        this.id_Inmuenle = id_Inmuenle;
    }

    public String getInmueble() {
        return Inmueble;
    }

    public void setInmueble(String Inmueble) {
        this.Inmueble = Inmueble;
    }

    public Inmueble(int id_Inmuenle, String Inmueble) {
        this.id_Inmuenle = id_Inmuenle;
        this.Inmueble = Inmueble;
    }

    public Inmueble() {
    }

    @Override
    public String toString() {
        return Inmueble;
    }

}

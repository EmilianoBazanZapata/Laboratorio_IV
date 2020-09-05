package Clases;

public class Nivel {

    public Nivel() {
    }

    private int id_nivel;
    private String nombreNivel;

    public int getId_nivel() {
        return id_nivel;
    }

    public void setId_nivel(int id_nivel) {
        this.id_nivel = id_nivel;
    }

    public String getNombreNivel() {
        return nombreNivel;
    }

    public void setNombreNivel(String nombreNivel) {
        this.nombreNivel = nombreNivel;
    }

    public Nivel(int id_nivel, String nombreNivel) {
        this.id_nivel = id_nivel;
        this.nombreNivel = nombreNivel;
    }

    @Override
    public String toString() {
        return nombreNivel;
    }

}

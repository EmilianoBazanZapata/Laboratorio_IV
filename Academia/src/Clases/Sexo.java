package Clases;

public class Sexo {

    public Sexo() {
    }

    private int idsexo;
    private String sexo;

    public int getIdsexo() {
        return idsexo;
    }

    public void setIdsexo(int idsexo) {
        this.idsexo = idsexo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return sexo;
    }

    public Sexo(int idsexo, String sexo) {
        this.idsexo = idsexo;
        this.sexo = sexo;
    }

}

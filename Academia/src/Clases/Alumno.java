package Clases;

public class Alumno {

    private int legajo;
    private String Apellido;
    private Curso curso;
    private Nivel nivel;
    private Sexo sexo;

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Alumno(int legajo, String Apellido, Curso curso, Nivel nivel,Sexo sexo) {
        this.legajo = legajo;
        this.Apellido = Apellido;
        this.curso = curso;
        this.nivel = nivel;
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Alumno{" + "legajo=" + legajo + ", Apellido=" + Apellido + ", curso=" + curso + ", nivel=" + nivel + '}';
    }

}

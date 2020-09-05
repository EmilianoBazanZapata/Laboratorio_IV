package Clases;

public class Curso {

    public Curso() {
    }

    private int idcurso;
    private String Nombre_curso;
    private int capacidad;
    private Turno turno;

    public int getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(int idcurso) {
        this.idcurso = idcurso;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getNombre_curso() {
        return Nombre_curso;
    }

    public void setNombre_curso(String Nombre_curso) {
        this.Nombre_curso = Nombre_curso;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Curso(int idcurso ,String Nombre_curso, Turno turno, int capacidad) {
        this.idcurso = idcurso;
        this.Nombre_curso = Nombre_curso;
        this.turno = turno;
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Cursos{" + ", Nombre_curso=" + Nombre_curso + ", turno=" + turno + '}';
    }

}

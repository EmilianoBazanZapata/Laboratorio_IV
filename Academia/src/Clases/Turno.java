package Clases;

public class Turno {

    public Turno() {
    }

    private int id_turno;
    private String turno;
    public int getId_turno() {
        return id_turno;
    }

    public void setId_turno(int id_turno) {
        this.id_turno = id_turno;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Turno(int id_turno, String turno) {
        this.id_turno = id_turno;
        this.turno = turno;
    }

    @Override
    public String toString() {
        return turno ;
    }

    
}

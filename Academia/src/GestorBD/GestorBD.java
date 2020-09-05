package GestorBD;

import Clases.Alumno;
import Clases.Curso;
import Clases.Nivel;
import Clases.PorcentajedeSexo;
import Clases.Sexo;
import Clases.Turno;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class GestorBD {

    private static final String CON
            = "jdbc:sqlserver://DESKTOP-EPDPOLD\\SQLEXPRESS:1433;databaseName=ACADEMIA";
    public String USER = "sa";
    public String PASS = "111624";
    public Connection con;

    private void AbrirConexion() {
        try {
            con = DriverManager.getConnection(CON, USER, PASS);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void CerrarConexion() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Turno> ObtenerTurnos() {
        ArrayList<Turno> lista = new ArrayList<Turno>();
        try {
            AbrirConexion();

            String Consulta = "select * from Turnos";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            while (rt.next()) {
                int id = rt.getInt("id_Turno");
                String nombre = rt.getString("Turno");

                Turno d = new Turno(id, nombre);

                lista.add(d);
            }
            rt.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return lista;
    }

    public ArrayList<Nivel> ObtenerNiveles() {
        ArrayList<Nivel> lista = new ArrayList<Nivel>();
        try {
            AbrirConexion();

            String Consulta = "select * from Niveles";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            while (rt.next()) {
                int id = rt.getInt("id_nivel");
                String nombre = rt.getString("nom_nivel");

                Nivel d = new Nivel(id, nombre);

                lista.add(d);
            }
            rt.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return lista;
    }

    public boolean AgregarCurso(Curso c) {
        boolean resultado = false;
        try {
            AbrirConexion();

            String Consulta = "insert into CURSOS values(?,?,?)";

            PreparedStatement st = con.prepareStatement(Consulta);

            st.setString(1, c.getNombre_curso());
            st.setInt(2, c.getTurno().getId_turno());
            st.setInt(3, c.getCapacidad());

            st.execute();

            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return resultado;
    }

    public ArrayList<Curso> ObtenerCursos() {
        ArrayList<Curso> lista = new ArrayList<Curso>();
        try {
            AbrirConexion();

            String Consulta = "select c.ID_CURSO , c.NOM_CURSO , c.CapacidadMaxima , t.TURNO \n"
                    + "from CURSOS c , TURNOS t \n"
                    + "where c.ID_TURNO = t.ID_TURNO";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            while (rt.next()) {

                int id = rt.getInt("ID_CURSO");
                String nombre = rt.getString("NOM_CURSO");

                String turno = rt.getString("TURNO");
                Turno t = new Turno();
                t.setTurno(turno);

                int capacidad = rt.getInt("CapacidadMaxima");

                Curso c = new Curso(id, nombre, t, capacidad);

                lista.add(c);
            }
            rt.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return lista;
    }

    public ArrayList<Alumno> ObtenerAlumnos() {
        ArrayList<Alumno> lista = new ArrayList<Alumno>();
        try {
            AbrirConexion();

            String Consulta = "select a.LEGAJO , a.NOM_ALUMNO , n.NOM_NIVEL ,c.NOM_CURSO ,c.ID_CURSO , s.SEXO\n"
                    + "from alumnos a , NIVELES n , CURSOS c ,SEXOS s\n"
                    + "where a.ID_NIVEL = n.ID_NIVEL\n"
                    + "and a.ID_CURSO = c.ID_CURSO\n"
                    + "and a.id_sexo = s.ID_SEXO";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            while (rt.next()) {

                int Legajo = rt.getInt("LEGAJO");
                String nombre = rt.getString("NOM_ALUMNO");

                String nivel = rt.getString("NOM_NIVEL");

                Nivel t = new Nivel();
                t.setNombreNivel(nivel);

                String nombrecurso = rt.getString("NOM_CURSO");
                int idcurso = rt.getInt("ID_CURSO");
                Curso cu = new Curso();
                cu.setNombre_curso(nombrecurso);
                cu.setIdcurso(idcurso);

                Sexo s = new Sexo();

                String sexo = rt.getString("SEXO");

                s.setSexo(sexo);

                Alumno c = new Alumno(Legajo, nombre, cu, t, s);

                lista.add(c);
            }
            rt.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return lista;
    }

    public boolean AgregarAlumno(Alumno c) {
        boolean resultado = false;
        try {
            AbrirConexion();

            String Consulta = "declare @capacidad int \n"
                    + "set @capacidad =(select c.CapacidadMaxima from CURSOS c where c.ID_CURSO = ?)\n"
                    + "begin\n"
                    + "if(@capacidad =0)\n"
                    + "print('se alcanzo el cupo maximo de alumnos')\n"
                    + "else\n"
                    + "if(@capacidad >0)\n"
                    + "insert into ALUMNOS values(?,?,?,?,?)\n"
                    + "end\n"
                    + "begin \n"
                    + "if(@capacidad >0)\n"
                    + "update cursos \n"
                    + "set CapacidadMaxima -=1\n"
                    + "where ID_CURSO = ?\n"
                    + "end\n"
                    + "print @capacidad";

            PreparedStatement st = con.prepareStatement(Consulta);

            st.setInt(1, c.getCurso().getIdcurso());
            st.setInt(2, c.getLegajo());
            st.setString(3, c.getApellido());
            st.setInt(4, c.getNivel().getId_nivel());
            st.setInt(5, c.getCurso().getIdcurso());
            st.setInt(6, c.getSexo().getIdsexo());
            st.setInt(7, c.getCurso().getIdcurso());

            st.execute();

            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return resultado;
    }

    public boolean ActualizarAlumno(Alumno c) {
        boolean resultado = false;
        try {
            AbrirConexion();

            String Consulta = "update ALUMNOS set \n"
                    + "NOM_ALUMNO = ?,\n"
                    + "ID_CURSO = ?,\n"
                    + "ID_NIVEL = ? \n"
                    + "where legajo = ?";

            PreparedStatement st = con.prepareStatement(Consulta);

            st.setString(1, c.getApellido());
            st.setInt(3, c.getNivel().getId_nivel());
            st.setInt(2, c.getCurso().getIdcurso());
            st.setInt(4, c.getLegajo());

            st.execute();

            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return resultado;
    }

    public boolean EliminarAlumno(int legajo, int id) {
        boolean resultado = false;
        try {
            AbrirConexion();

            String Consulta = "delete alumnos \n"
                    + "where LEGAJO = ?\n"
                    + "update cursos\n"
                    + "set CapacidadMaxima += 1\n"
                    + "where ID_CURSO = ?";

            PreparedStatement st = con.prepareStatement(Consulta);

            st.setInt(1, legajo);
            st.setInt(2, id);
            st.execute();

            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return resultado;
    }

    public ArrayList<Sexo> ObtenerSexos() {
        ArrayList<Sexo> lista = new ArrayList<Sexo>();
        try {
            AbrirConexion();

            String Consulta = "select * from Sexos";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            while (rt.next()) {
                int id = rt.getInt("id_sexo");
                String nombre = rt.getString("sexo");

                Sexo d = new Sexo(id, nombre);

                lista.add(d);
            }
            rt.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return lista;
    } //listo

    public ArrayList<PorcentajedeSexo> Obtenergeneros(int idCurso) {
        ArrayList<PorcentajedeSexo> lista = new ArrayList<PorcentajedeSexo>();
        try {
            AbrirConexion();

            String Consulta = "declare @total float \n"
                    + "set @total =(select count(a.id_sexo)from alumnos a where a.ID_CURSO =?)\n"
                    + "print @total \n"
                    + "\n"
                    + "declare @masculino int\n"
                    + "declare @femenino int\n"
                    + "declare @otro int\n"
                    + "\n"
                    + "\n"
                    + "if (@total = 0)\n"
                    + " \n"
                    + " select @femenino'femenino', @masculino'masculino', @otro'otro';\n"
                    + "\n"
                    + "\n"
                    + "else\n"
                    + "\n"
                    + "\n"
                    + "set @masculino =(select count(a.id_sexo)\n"
                    + "from alumnos a \n"
                    + "where a.ID_SEXO = 1 \n"
                    + "and a.ID_CURSO = ?)\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "set @femenino =(select count(a.id_sexo)\n"
                    + "from alumnos a \n"
                    + "where a.ID_SEXO = 2 \n"
                    + "and a.ID_CURSO = ?)\n"
                    + "print @femenino\n"
                    + "\n"
                    + "\n"
                    + "set @otro =(select count(a.id_sexo)\n"
                    + "from alumnos a \n"
                    + "where a.ID_SEXO = 3 \n"
                    + "and a.ID_CURSO = ?)\n"
                    + "print @otro\n"
                    + "if (@total > 0)\n"
                    + "select (@femenino/@total)*100 as'femenino', (@masculino/@total)*100 as'masculino', (@otro/@total)*100 as'otro'";
            
            
            PreparedStatement st = con.prepareStatement(Consulta);
            
            st.setInt(1, idCurso);
            st.setInt(2, idCurso);
            st.setInt(3, idCurso);
            st.setInt(4, idCurso);
            ResultSet rt = st.executeQuery();

            while (rt.next()) {
                float masculino = rt.getInt("masculino");
                float femenino = rt.getInt("femenino");
                float sin = rt.getInt("otro");
                PorcentajedeSexo l = new PorcentajedeSexo(masculino, femenino, sin);

                lista.add(l);
            }
            rt.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return lista;
    }

    public ArrayList<Alumno> ObtenerAlumnosporCurso(int idCurso) {
        ArrayList<Alumno> lista = new ArrayList<Alumno>();
        try {
            AbrirConexion();

            String Consulta = "select a.LEGAJO , a.NOM_ALUMNO , n.NOM_NIVEL ,c.NOM_CURSO ,c.ID_CURSO , s.SEXO\n"
                    + "                    from alumnos a , NIVELES n , CURSOS c , SEXOS s\n"
                    + "                    where a.ID_NIVEL = n.ID_NIVEL\n"
                    + "                    and a.ID_CURSO = c.ID_CURSO\n"
                    + "					and a.id_sexo = s.ID_SEXO\n"
                    + "					and c.ID_CURSO =?";
            PreparedStatement st = con.prepareStatement(Consulta);
            st.setInt(1, idCurso);
            ResultSet rt = st.executeQuery();

            while (rt.next()) {

                int Legajo = rt.getInt("LEGAJO");
                String nombre = rt.getString("NOM_ALUMNO");

                String nivel = rt.getString("NOM_NIVEL");

                Nivel t = new Nivel();
                t.setNombreNivel(nivel);

                String nombrecurso = rt.getString("NOM_CURSO");
                int idcurso = rt.getInt("ID_CURSO");
                Curso cu = new Curso();
                cu.setNombre_curso(nombrecurso);
                cu.setIdcurso(idcurso);

                Sexo s = new Sexo();

                String sexo = rt.getString("SEXO");

                s.setSexo(sexo);

                Alumno c = new Alumno(Legajo, nombre, cu, t, s);

                lista.add(c);
            }
            rt.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return lista;
    }

    public ArrayList<Alumno> ObtenerAlumnosPorNivel(String idnivel) {
        ArrayList<Alumno> lista = new ArrayList<Alumno>();
        try {
            AbrirConexion();

            String Consulta = "select a.LEGAJO , a.NOM_ALUMNO , n.NOM_NIVEL ,c.NOM_CURSO ,c.ID_CURSO , s.SEXO\n"
                    + "        from alumnos a , NIVELES n , CURSOS c , SEXOS s\n"
                    + "        where a.ID_NIVEL = n.ID_NIVEL\n"
                    + "        and a.ID_CURSO = c.ID_CURSO\n"
                    + "	       and a.id_sexo = s.ID_SEXO\n"
                    + "	       and n.NOM_NIVEL = ?";
            PreparedStatement st = con.prepareStatement(Consulta);
            st.setString(1, idnivel);
            ResultSet rt = st.executeQuery();

            while (rt.next()) {

                int Legajo = rt.getInt("LEGAJO");
                String nombre = rt.getString("NOM_ALUMNO");

                String nivel = rt.getString("NOM_NIVEL");

                Nivel t = new Nivel();
                t.setNombreNivel(nivel);

                String nombrecurso = rt.getString("NOM_CURSO");
                int idcurso = rt.getInt("ID_CURSO");
                Curso cu = new Curso();
                cu.setNombre_curso(nombrecurso);
                cu.setIdcurso(idcurso);

                Sexo s = new Sexo();

                String sexo = rt.getString("SEXO");

                s.setSexo(sexo);

                Alumno c = new Alumno(Legajo, nombre, cu, t, s);

                lista.add(c);
            }
            rt.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return lista;
    }

    public ArrayList<Alumno> ObtenerAlumnosTarde() {
        ArrayList<Alumno> lista = new ArrayList<Alumno>();
        try {
            AbrirConexion();

            String Consulta = "					select a.LEGAJO , a.NOM_ALUMNO , n.NOM_NIVEL ,c.NOM_CURSO ,c.ID_CURSO , s.SEXO , t.TURNO\n"
                    + "                    from alumnos a , NIVELES n , CURSOS c , SEXOS s , TURNOS t \n"
                    + "                    where a.ID_NIVEL = n.ID_NIVEL\n"
                    + "                    and a.ID_CURSO = c.ID_CURSO\n"
                    + "					and a.id_sexo = s.ID_SEXO\n"
                    + "					and c.ID_TURNO = t.ID_TURNO\n"
                    + "					and t.ID_TURNO = 2";
            Statement st = con.createStatement();
            ResultSet rt = st.executeQuery(Consulta);

            while (rt.next()) {

                int Legajo = rt.getInt("LEGAJO");
                String nombre = rt.getString("NOM_ALUMNO");

                String nivel = rt.getString("NOM_NIVEL");

                Nivel t = new Nivel();
                t.setNombreNivel(nivel);

                String nombrecurso = rt.getString("NOM_CURSO");
                int idcurso = rt.getInt("ID_CURSO");
                Curso cu = new Curso();
                cu.setNombre_curso(nombrecurso);
                cu.setIdcurso(idcurso);

                Sexo s = new Sexo();

                String sexo = rt.getString("SEXO");

                s.setSexo(sexo);

                Alumno c = new Alumno(Legajo, nombre, cu, t, s);

                lista.add(c);
            }
            rt.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return lista;
    } //listo
}

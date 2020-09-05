/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestor;

import Clases.Autor;
import Clases.Libro;
import Clases.Nacionalidad;
import Clases.Prestamo;
import Clases.Reporte;
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

/**
 *
 * @author Emiliano
 */
public class GestorBD {

    private static final String CON
            = "jdbc:sqlserver://DESKTOP-EPDPOLD\\SQLEXPRESS:1433;databaseName=LIBRERIA";
    private String USER = "sa";
    private String PASS = "111624";
    private Connection con;

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

    public ArrayList<Nacionalidad> ObtenerProductos() {
        ArrayList<Nacionalidad> lista = new ArrayList<Nacionalidad>();
        try {
            AbrirConexion();

            String Consulta = "select * from Nacionalidades";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            while (rt.next()) {
                int id = rt.getInt("IdNacionalidad");
                String nombre = rt.getString("Nacionalidad");

                Nacionalidad d = new Nacionalidad(id, nombre);
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

    public boolean AgregarAutor(Autor a) {
        boolean resultado = false;
        try {
            AbrirConexion();

            String Consulta = "insert into Autores values(?,?,?)";

            PreparedStatement st = con.prepareStatement(Consulta);

            st.setInt(3, a.getDni());
            st.setString(1, a.getNombre());
            st.setInt(2, a.getNacionalidad().getIdNacionalidad());

            st.execute();

            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return resultado;
    } //listo

    public ArrayList<Autor> ObtenerAutores() {
        ArrayList<Autor> lista = new ArrayList<Autor>();
        try {
            AbrirConexion();

            String Consulta = "select A.*, N.Nacionalidad\n"
                    + "from Autores A , Nacionalidades N \n"
                    + "where A.idNacionalidad  =N.idNacionalidad";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            while (rt.next()) {
                int id = rt.getInt("Dni");
                String nombre = rt.getString("Nombre");

                String Nacionalidad = rt.getString("Nacionalidad");

                Nacionalidad p = new Nacionalidad();

                p.setNacionalidad(Nacionalidad);

                Autor d = new Autor(id, nombre, p);

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

    public ArrayList<Prestamo> ObtenerPrestamos() {
        ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
        try {
            AbrirConexion();

            String Consulta = "select * from prestamos";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            while (rt.next()) {
                int id = rt.getInt("idPrestamo");
                String nombre = rt.getString("Prestamo");

                Prestamo d = new Prestamo(id, nombre);

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

    public boolean AgregarLibro(Libro l) {
        boolean resultado = false;
        try {
            AbrirConexion();

            String Consulta = "insert into Libros values(?,?,?,?)";

            PreparedStatement st = con.prepareStatement(Consulta);

            st.setInt(1, l.getCodigo());
            st.setString(2, l.getDescripcion());
            st.setInt(3, l.getAutor().getDni());
            st.setInt(4, l.getPrestamo().getIdPrestamo());

            st.execute();

            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return resultado;
    } //listo

    public ArrayList<Libro> ObtenerLibros() {
        ArrayList<Libro> lista = new ArrayList<Libro>();
        try {
            AbrirConexion();

            String Consulta = " select l.Codigo,l.Descripcion,a.DNI ,a.Nombre ,n.Nacionalidad,p.Prestamo\n"
                    + " from libros  l ,Autores a , Prestamos p ,Nacionalidades n\n"
                    + " where l.DniAutor  = a.DNI\n"
                    + " and l.idPrestamo = p.idPrestamo\n"
                    + " and a.idNacionalidad = a.idNacionalidad\n"
                    + " and a.idNacionalidad = n.idNacionalidad";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            while (rt.next()) {
                int id = rt.getInt("Codigo");
                String Descripcion = rt.getString("Descripcion");
                String Prestamo = rt.getString("Prestamo");
                int Dni = rt.getInt("Dni");
                String Nombre = rt.getString("Nombre");
                String Nacionalidad = rt.getString("Nacionalidad");
                Prestamo p = new Prestamo();

                p.setPrestamo(Prestamo);

                Autor a = new Autor();

                a.setDni(Dni);

                a.setNombre(Nombre);

                Nacionalidad n = new Nacionalidad();
                n.setNacionalidad(Nacionalidad);

                a.setNacionalidad(n);

                Libro l = new Libro(id, Descripcion, p, a);

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
    } //listo

    public ArrayList<Libro> ObtenerLibrosNacionales() {
        ArrayList<Libro> lista = new ArrayList<Libro>();
        try {
            AbrirConexion();

            String Consulta = " select a.Nombre ,n.Nacionalidad, l.Codigo , l.Descripcion\n"
                    + " from autores a , Libros l  ,Nacionalidades n \n"
                    + " where a.DNI = l.DniAutor\n"
                    + " and a.idNacionalidad = n.idNacionalidad\n"
                    + " and n.Nacionalidad = 'Argentino/a'";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            while (rt.next()) {
                int id = rt.getInt("Codigo");
                String Descripcion = rt.getString("Descripcion");
                String Nombre = rt.getString("Nombre");
                String Nacionalidad = rt.getString("Nacionalidad");
                Prestamo p = new Prestamo();

                p.setPrestamo("");

                Autor a = new Autor();

                a.setNombre(Nombre);

                Nacionalidad n = new Nacionalidad();

                n.setNacionalidad(Nacionalidad);

                a.setNacionalidad(n);

                Libro l = new Libro(id, Descripcion, p, a);

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
    } //listo

    public ArrayList<Reporte> cantidaddelibros() {
        ArrayList<Reporte> lista = new ArrayList<Reporte>();
        try {
            AbrirConexion();

            String Consulta = " select count(*) as 'cantidaddelibros'\n"
                    + " from Libros";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            if (rt.next()) {
                int id = rt.getInt("cantidaddelibros");

                Reporte l = new Reporte(id, 0);

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
    } //listo

    public ArrayList<Reporte> PorcentajesoloLectura() {
        ArrayList<Reporte> lista = new ArrayList<Reporte>();
        try {
            AbrirConexion();

            String Consulta = " declare @total_de_libros float \n"
                    + " set @total_de_libros =(select count(*)from libros)\n"
                    + "\n"
                    + " select (count(l.Codigo) /@total_de_libros)*100 as 'total'\n"
                    + " from Libros l ,Prestamos p \n"
                    + " where l.idPrestamo = p.idPrestamo\n"
                    + " and p.idPrestamo = 1";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            if (rt.next()) {
                float porcentaje = rt.getFloat("total");

                Reporte l = new Reporte(0, porcentaje);

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
    } //listo

    public ArrayList<Libro> ObtenerAutores(int dni) {
        ArrayList<Libro> lista = new ArrayList<Libro>();
        try {
            AbrirConexion();

            String Consulta = " select a.Nombre , a.DNI ,n.Nacionalidad, l.Codigo , l.Descripcion\n"
                    + " from autores a , libros l , Nacionalidades n\n"
                    + " where a.DNI = l.DniAutor\n"
                    + " and a.idNacionalidad = n.idNacionalidad\n"
                    + " and a.DNI = (?)";

            PreparedStatement st = con.prepareStatement(Consulta);
            st.setInt(1, dni);
            ResultSet rt = st.executeQuery();

            while (rt.next()) {
                int id = rt.getInt("Codigo");
                String Descripcion = rt.getString("Descripcion");

                int Dni = rt.getInt("Dni");
                String Nombre = rt.getString("Nombre");
                String Nacionalidad = rt.getString("Nacionalidad");
                Prestamo p = new Prestamo();

                p.setPrestamo("");

                Autor a = new Autor();

                a.setDni(Dni);

                a.setNombre(Nombre);

                Nacionalidad n = new Nacionalidad();
                n.setNacionalidad(Nacionalidad);

                a.setNacionalidad(n);

                Libro l = new Libro(id, Descripcion, p, a);

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
    } //listo
}

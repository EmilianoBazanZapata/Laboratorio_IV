package GestorBD;

import Clases.Componente;
import Clases.Produccion;
import Clases.Reporte;
import Clases.ReporteProduccion;
import Clases.Responsable;
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
            = "jdbc:sqlserver://DESKTOP-EPDPOLD\\SQLEXPRESS:1433;databaseName=PRODUCCIONES";
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

    public boolean AgregarResponsable(Responsable r) {
        boolean resultado = false;
        try {
            AbrirConexion();

            String Consulta = "insert into RESPONSABLES values(?)";

            PreparedStatement st = con.prepareStatement(Consulta);

            st.setString(1, r.getNombreResponssable());

            st.execute();

            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return resultado;
    }

    public ArrayList<Responsable> ObtenerProductos() {
        ArrayList<Responsable> lista = new ArrayList<Responsable>();
        try {
            AbrirConexion();

            String Consulta = "select * from RESPONSABLES";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            while (rt.next()) {
                int id = rt.getInt("ID_RESPONSABLE");
                String nombre = rt.getString("RESPONSABLE");

                Responsable d = new Responsable(id, nombre);

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

    public boolean ActualizarResponsable(Responsable r) {
        boolean resultado = false;
        try {
            AbrirConexion();

            String Consulta = "  update RESPONSABLES\n"
                    + "  set RESPONSABLE  =?\n"
                    + "  where ID_RESPONSABLE = ?";

            PreparedStatement st = con.prepareStatement(Consulta);

            st.setString(1, r.getNombreResponssable());
            st.setInt(2, r.getIdResponsable());

            st.execute();

            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return resultado;
    }

    public boolean AgregarComponente(Componente c) {
        boolean resultado = false;
        try {
            AbrirConexion();

            String Consulta = "insert into Componentes values(?)";

            PreparedStatement st = con.prepareStatement(Consulta);

            st.setString(1, c.getDescripcion());

            st.execute();

            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return resultado;
    }

    public ArrayList<Componente> ObtenerComponentes() {
        ArrayList<Componente> lista = new ArrayList<Componente>();
        try {
            AbrirConexion();

            String Consulta = "select * from Componentes";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            while (rt.next()) {
                int id = rt.getInt("ID_COMPONENTE");
                String nombre = rt.getString("DESCRIPCION");

                Componente d = new Componente(id, nombre);

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

    public boolean ActualizarComponente(Componente r) {
        boolean resultado = false;
        try {
            AbrirConexion();

            String Consulta = "update COMPONENTES\n"
                    + "set DESCRIPCION = ?\n"
                    + "where ID_COMPONENTE = ?";

            PreparedStatement st = con.prepareStatement(Consulta);

            st.setString(1, r.getDescripcion());
            st.setInt(2, r.getIdComponente());

            st.execute();

            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return resultado;
    }

    public boolean AgregarProduccion(Produccion p) {
        boolean resultado = false;
        try {
            AbrirConexion();

            String Consulta = "insert into PRODUCCION values(?,?,?,?)";

            PreparedStatement st = con.prepareStatement(Consulta);

            st.setInt(1, p.getCantidad());
            st.setInt(2, p.getComponente().getIdComponente());
            st.setInt(3, p.getResponsable().getIdResponsable());
            st.setString(4, p.getFecha());
            st.execute();

            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return resultado;
    }

    public ArrayList<Reporte> Reporte(int id) {
        ArrayList<Reporte> lista = new ArrayList<Reporte>();
        try {
            AbrirConexion();

            String Consulta = "  select r.RESPONSABLE as 'responsable',count(p.FECHA) as'cantidad'\n"
                    + "  from PRODUCCION p, RESPONSABLES r  \n"
                    + "  where p.ID_RESPONSABLE = r.ID_RESPONSABLE\n"
                    + "  and p.ID_RESPONSABLE = ?\n"
                    + "  group by r.RESPONSABLE";
            PreparedStatement st = con.prepareStatement(Consulta);
            st.setInt(1, id);

            ResultSet rt = st.executeQuery();

            if (rt.next()) {
                int cantidad = rt.getInt("cantidad");
                String nombre = rt.getString("responsable");
                Reporte l = new Reporte(cantidad, nombre);

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

    public ArrayList<Reporte> ReporteTodos() {
        ArrayList<Reporte> lista = new ArrayList<Reporte>();
        try {
            AbrirConexion();

            String Consulta = "  select r.RESPONSABLE,count(p.FECHA) as'cantidad'\n"
                    + "  from PRODUCCION p right join RESPONSABLES r  on p.ID_RESPONSABLE = r.ID_RESPONSABLE\n"
                    + "  --where  p.ID_RESPONSABLE = 1\n"
                    + "  group by r.RESPONSABLE";
            PreparedStatement st = con.prepareStatement(Consulta);
            ResultSet rt = st.executeQuery();

            while (rt.next()) {
                int cantidad = rt.getInt("cantidad");
                String nombre = rt.getString("responsable");
                Reporte l = new Reporte(cantidad, nombre);

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

    public ArrayList<ReporteProduccion> ReporteProduccion() {
        ArrayList<ReporteProduccion> lista = new ArrayList<ReporteProduccion>();
        try {
            AbrirConexion();

            String Consulta = "  select p.ID_PRO , p.CANTIDAD_PRO , p.FECHA , r.RESPONSABLE\n"
                    + "  from PRODUCCION p , RESPONSABLES r \n"
                    + "  where p.ID_RESPONSABLE = r.ID_RESPONSABLE";
            Statement st = con.createStatement();
            ResultSet rt = st.executeQuery(Consulta);

            while (rt.next()) {
                int cantidad = rt.getInt("CANTIDAD_PRO");
                String nombre = rt.getString("RESPONSABLE");
                int Id = rt.getInt("ID_PRO");
                String fecha = rt.getString("FECHA");
                ReporteProduccion l = new ReporteProduccion(Id, cantidad, fecha, nombre);

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


    public boolean Actualizar(int id, String nombre, int cantidad) {
        boolean resultado = false;
        try {
            AbrirConexion();

            String Consulta = "  update PRODUCCION \n"
                    + "  set fecha = ?,\n"
                    + "  CANTIDAD_PRO = ?\n"
                    + "  where id_pro = ?";

            PreparedStatement st = con.prepareStatement(Consulta);

            st.setString(1, nombre);
            st.setInt(2, cantidad);
            st.setInt(3, id);

            st.execute();

            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return resultado;
    }
}

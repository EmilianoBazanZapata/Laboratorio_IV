package GestorBD;

import Clases.Inmueble;
import Clases.Operacion;
import Clases.Transaccion;
import Clases.Vendedor;
import Reporte.DTOPorcentajeTipoAlquiler;
import Reporte.DTOmayortransaccion;
import Reporte.DTOtransaccionesdeoficina;
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
            = "jdbc:sqlserver://DESKTOP-EPDPOLD\\SQLEXPRESS:1433;databaseName=INMOBILIARIA";
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

    public ArrayList<Vendedor> ObtenerVendedores() {
        ArrayList<Vendedor> lista = new ArrayList<Vendedor>();
        try {
            AbrirConexion();

            String Consulta = "select * from Vendedores";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            while (rt.next()) {
                int id = rt.getInt("ID_VENDEDOR");
                String nombre = rt.getString("NOMBRE_VENDEDOR");

                Vendedor d = new Vendedor(id, nombre);

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

    public ArrayList<Operacion> ObtenerOperaciones() {
        ArrayList<Operacion> lista = new ArrayList<Operacion>();
        try {
            AbrirConexion();

            String Consulta = "select * from Operaciones";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            while (rt.next()) {
                int id = rt.getInt("ID_OPERACION");
                String nombre = rt.getString("OPERACION");

                Operacion d = new Operacion(id, nombre);

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

    public ArrayList<Inmueble> ObtenerInmuebles() {
        ArrayList<Inmueble> lista = new ArrayList<Inmueble>();
        try {
            AbrirConexion();

            String Consulta = "select * from Inmuebles";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            while (rt.next()) {
                int id = rt.getInt("ID_INMUEBLE");
                String nombre = rt.getString("INMUEBLE");

                Inmueble d = new Inmueble(id, nombre);

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

    public boolean AgregarTransaccion(Transaccion t) {
        boolean resultado = false;
        try {
            AbrirConexion();

            String Consulta = "insert into TRANSACCIONES values(?,?,?,?,?)";

            PreparedStatement st = con.prepareStatement(Consulta);

            st.setString(1, t.getFecha());
            st.setInt(2, t.getVendedor().getId_Vendedor());
            st.setInt(3, t.getInmueble().getId_Inmuenle());
            st.setInt(4, t.getOperacion().getId_Operacion());
            st.setFloat(5, t.getMonto());
            st.execute();

            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            CerrarConexion();
        }
        return resultado;
    }

    public ArrayList<Transaccion> ObtenerTransacciones() {
        ArrayList<Transaccion> lista = new ArrayList<Transaccion>();
        try {
            AbrirConexion();

            String Consulta = "select t.FECHA , t.MONTO , v.NOMBRE_VENDEDOR , i.INMUEBLE , o.OPERACION\n"
                    + "from TRANSACCIONES t , VENDEDORES v , INMUEBLES i , OPERACIONES o\n"
                    + "where t.ID_VENDEDOR = v.ID_VENDEDOR\n"
                    + "and t.ID_INMUEBLE = i.ID_INMUEBLE\n"
                    + "and t.ID_OPERACION = o.ID_OPERACION";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            while (rt.next()) {
                String fecha = rt.getString("FECHA");
                float monto = rt.getFloat("MONTO");

                String vendedor = rt.getString("NOMBRE_VENDEDOR");
                Vendedor v = new Vendedor();
                v.setVendedor(vendedor);

                String inmueble = rt.getString("INMUEBLE");
                Inmueble i = new Inmueble();
                i.setInmueble(inmueble);

                String operacion = rt.getString("OPERACION");

                Operacion o = new Operacion();
                o.setOperacion(operacion);
                Transaccion d = new Transaccion(0, fecha, monto, v, o, i);

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

    public ArrayList<DTOmayortransaccion> ObtenerMayorTransaccion() {
        ArrayList<DTOmayortransaccion> lista = new ArrayList<DTOmayortransaccion>();
        try {
            AbrirConexion();

            String Consulta = "select t.FECHA ,t.MONTO , v.NOMBRE_VENDEDOR , i.INMUEBLE , o.OPERACION\n"
                    + "from TRANSACCIONES t , VENDEDORES v , INMUEBLES i , OPERACIONES o\n"
                    + "where t.ID_VENDEDOR = v.ID_VENDEDOR\n"
                    + "and t.ID_INMUEBLE = i.ID_INMUEBLE\n"
                    + "and t.ID_OPERACION = o.ID_OPERACION\n"
                    + "and t.MONTO = (select max(t.MONTO) from transacciones t)";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            if (rt.next()) {
                String fecha = rt.getString("FECHA");
                float monto = rt.getFloat("MONTO");

                String vendedor = rt.getString("NOMBRE_VENDEDOR");
                Vendedor v = new Vendedor();
                v.setVendedor(vendedor);

                String inmueble = rt.getString("INMUEBLE");
                Inmueble i = new Inmueble();
                i.setInmueble(inmueble);

                String operacion = rt.getString("OPERACION");

                Operacion o = new Operacion();
                o.setOperacion(operacion);
                DTOmayortransaccion d = new DTOmayortransaccion(0, fecha, monto, v, o, i);

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

    public ArrayList<DTOPorcentajeTipoAlquiler> ObtenerPorcentaje() {
        ArrayList<DTOPorcentajeTipoAlquiler> lista = new ArrayList<DTOPorcentajeTipoAlquiler>();
        try {
            AbrirConexion();

            String Consulta = "declare @totaltransacciones int\n"
                    + "set @totaltransacciones=(select count(*) from transacciones)\n"
                    + "\n"
                    + "declare @transaccionestiporalquiler float\n"
                    + "set @transaccionestiporalquiler=(select count(*) \n"
                    + "                                from transacciones t , OPERACIONES o\n"
                    + "								where t.ID_OPERACION = o.ID_OPERACION\n"
                    + "								and o.ID_OPERACION=1)\n"
                    + "\n"
                    + "select ((@transaccionestiporalquiler / @totaltransacciones)*100) as'porcentaje'";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            if (rt.next()) {
                float porcentaje = rt.getFloat("porcentaje");

                DTOPorcentajeTipoAlquiler d = new DTOPorcentajeTipoAlquiler(porcentaje);

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

    public ArrayList<DTOtransaccionesdeoficina> ObtenerTransacciontipoOficina() {
        ArrayList<DTOtransaccionesdeoficina> lista = new ArrayList<DTOtransaccionesdeoficina>();
        try {
            AbrirConexion();

            String Consulta = "select t.FECHA , t.MONTO , v.NOMBRE_VENDEDOR , i.INMUEBLE , o.OPERACION\n"
                    + "from TRANSACCIONES t , VENDEDORES v , INMUEBLES i , OPERACIONES o\n"
                    + "where t.ID_VENDEDOR = v.ID_VENDEDOR\n"
                    + "and t.ID_INMUEBLE = i.ID_INMUEBLE\n"
                    + "and t.ID_OPERACION = o.ID_OPERACION\n"
                    + "and t.ID_INMUEBLE = 1";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);

            while (rt.next()) {
                String fecha = rt.getString("FECHA");
                float monto = rt.getFloat("MONTO");

                String vendedor = rt.getString("NOMBRE_VENDEDOR");
                Vendedor v = new Vendedor();
                v.setVendedor(vendedor);

                String inmueble = rt.getString("INMUEBLE");
                Inmueble i = new Inmueble();
                i.setInmueble(inmueble);

                String operacion = rt.getString("OPERACION");

                Operacion o = new Operacion();
                o.setOperacion(operacion);
                DTOtransaccionesdeoficina d = new DTOtransaccionesdeoficina(0, fecha, monto, v, o, i);

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
}

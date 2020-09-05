package Controlador;

//imports necesatrios para evitar problemas 
//podria agregar el * pero tardaria mas en iniciar
import Modelo.Producto;
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

    //creo las variables con la informacion necesaria 
    //asi luego le paso la informacion por parametros al metodo : AbrirConexion
    private static final String CON
            = "jdbc:sqlserver://DESKTOP-EPDPOLD\\SQLEXPRESS:1433;databaseName=PRODUCTOS";
    private String USER = "sa";
    private String PASS = "111624";
    private Connection con;

    // el metodo necesario para abrir la conexion con sql server
    private void AbrirConexion() {

        //utilizo un try catch para verificar que pueda abrir la conexion 
        try {
            con = DriverManager.getConnection(CON, USER, PASS);

        } catch (SQLException ex) {
            //si la conexion no se abre muestro el exception con un mensaje
            ex.printStackTrace();
        }
    }

    //metodo para cerrar la conexion con sql server
    private void CerrarConexion() {
        //verifico si a conexion esta abierta 
        //si es asi se cerrara
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException ex) {
            //si no esposible cerrarla se mostrara el exception con un mensaje
            ex.printStackTrace();
        }
    }

    //metodo para agregar un producto
    public boolean AgregarVenta(Producto producto) {
        boolean resultado = false;
        try {
            //abro la conexion
            AbrirConexion();

            //creo una variable con la sentencia de sql necesaria
            String Consulta = "insert into PRODUCTOS values(?,?)";

            PreparedStatement st = con.prepareStatement(Consulta);

            //asigno los valores a cada ? , respetando el orden correcto
            st.setString(1, producto.getNombre());
            st.setDouble(2, producto.getPrecio());

            st.execute();

            resultado = true;
        } catch (SQLException ex) {
            //si no es posible realizar la consulta se mostrara el exception con un mensaje
            ex.printStackTrace();
        } finally {
            //cierro la conexion
            CerrarConexion();
        }
        return resultado;
    }

    public ArrayList<Producto> ObtenerProductos() {
        //creo un arraylist de productos
        ArrayList<Producto> lista = new ArrayList<Producto>();
        try {
            //abro la conexion
            AbrirConexion();
            //creo una variable con la consulta sql
            String Consulta = "select * from PRODUCTOS";

            Statement st = con.createStatement();

            ResultSet rt = st.executeQuery(Consulta);
            //mientras que pueda ler datos los voy agregando a la lista
            while (rt.next()) {
                int id = rt.getInt("id");
                String nombre = rt.getString("NOMBRE");
                int precio = rt.getInt("PRECIO");

                Producto d = new Producto(id, nombre, precio);

                lista.add(d);
            }
            rt.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //cierro la conexion 
            CerrarConexion();
        }
        //retirno la lista con todos los datos cargados
        return lista;
    }

    public boolean Actualizar(Producto p) {
        //creo una variable booleana para saber si la consulta se ejecuta correctamente
        boolean resultado = false;
        try {
            //abro la conexion
            AbrirConexion();

            //creo una variable con la consulta sql 
            String Consulta = "UPDATE PRODUCTOS\n"
                    + "   SET NOMBRE = (?)\n"
                    + "      ,PRECIO = (?)\n"
                    + " WHERE ID  = (?)";
            PreparedStatement st = con.prepareStatement(Consulta);
            //tomo todos los valores que pasas por parametros
            st.setString(1, p.getNombre());
            st.setDouble(2, p.getPrecio());
            st.setInt(3, p.getId());
            //actualizo la consulta
            st.executeUpdate();
            //cierro la conexion 
            con.close();
            //retorno un tru al ejecutar correctamente la consulta
            resultado = true;

        } catch (Exception e) {
        }

        return resultado;
    }

    public boolean BorrarVenta(Producto p) {//creo una variable booleana para saber si la consulta se ejecuta correctamente
        boolean resultado = false;
        //abro la conexion
        AbrirConexion();
        try {
            //creo una variable con la consulta sql 
            String Consulta = "delete from PRODUCTOS where id = (?)";

            PreparedStatement st = con.prepareStatement(Consulta);

            st.setInt(1, p.getId());
            //ejecuto la consulta
            st.execute();
            //cierro la conexion
            con.close();
            resultado = true;
        } catch (Exception e) {
        }
        return resultado;
    }
}

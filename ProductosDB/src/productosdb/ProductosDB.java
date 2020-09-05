
package productosdb;

import Vista.frmProducto;


public class ProductosDB {


    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        frmProducto p = new frmProducto();
        p.setVisible(true);
    }
    
}

package produccion;

import Vistas.frmMenu;

public class Menu {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        frmMenu m = new frmMenu();
        m.setVisible(true);
    }

}

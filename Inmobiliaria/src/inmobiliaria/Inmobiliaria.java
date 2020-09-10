package inmobiliaria;

import Vistas.frmAgregarTransaccion;

public class Inmobiliaria {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        frmAgregarTransaccion at = new frmAgregarTransaccion();
        at.setVisible(true);
    }

}

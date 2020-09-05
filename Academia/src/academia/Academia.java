package academia;

import Vistas.AgregarCurso;

public class Academia {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        AgregarCurso al = new AgregarCurso();
        al.setVisible(true);
    }

}

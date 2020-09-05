/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import Vistas.frmAgregarAutor;

/**
 *
 * @author Emiliano
 */
public class Biblioteca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        frmAgregarAutor A = new frmAgregarAutor();
        A.setVisible(true);
    }
    
}

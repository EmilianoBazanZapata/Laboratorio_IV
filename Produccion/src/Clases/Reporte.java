/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Emiliano
 */
public class Reporte {

    public Reporte(int Cantidad, String Propietario) {
        this.Cantidad = Cantidad;
        this.Propietario = Propietario;
    }

    private int Cantidad;
    private String Propietario;

    public String getPropietario() {
        return Propietario;
    }

    public void setPropietario(String Propietario) {
        this.Propietario = Propietario;
    }

    public Reporte(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public int getCantidad() {
        return Cantidad;
    }


}

package Reporte;

import Clases.Inmueble;
import Clases.Operacion;
import Clases.Vendedor;

public class DTOmayortransaccion {

    private int id_Transaccion;
    private String fecha;
    private float monto;
    private Vendedor vendedor;
    private Operacion operacion;
    private Inmueble inmueble;

    public int getId_Transaccion() {
        return id_Transaccion;
    }

    public void setId_Transaccion(int id_Transaccion) {
        this.id_Transaccion = id_Transaccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public DTOmayortransaccion(int id_Transaccion, String fecha, float monto, Vendedor vendedor, Operacion operacion, Inmueble inmueble) {
        this.id_Transaccion = id_Transaccion;
        this.fecha = fecha;
        this.monto = monto;
        this.vendedor = vendedor;
        this.operacion = operacion;
        this.inmueble = inmueble;
    }

    public DTOmayortransaccion() {
    }

}

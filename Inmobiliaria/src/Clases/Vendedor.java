package Clases;

public class Vendedor {

    private int id_Vendedor;
    private String Vendedor;

    public int getId_Vendedor() {
        return id_Vendedor;
    }

    public void setId_Vendedor(int id_Vendedor) {
        this.id_Vendedor = id_Vendedor;
    }

    public String getVendedor() {
        return Vendedor;
    }

    public void setVendedor(String Vendedor) {
        this.Vendedor = Vendedor;
    }

    public Vendedor() {
    }

    public Vendedor(int id_Vendedor, String Vendedor) {
        this.id_Vendedor = id_Vendedor;
        this.Vendedor = Vendedor;
    }

    @Override
    public String toString() {
        return Vendedor;
    }
}

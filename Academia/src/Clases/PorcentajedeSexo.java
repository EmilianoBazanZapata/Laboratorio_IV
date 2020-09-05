package Clases;

public class PorcentajedeSexo {

    public PorcentajedeSexo() {
    }

    private float CantiMasculino;
    private float CantiFemenenino;
    private float CantidadSinSexo;

    public float getCantidadSinSexo() {
        return CantidadSinSexo;
    }

    public void setCantidadSinSexo(float CantidadSinSexo) {
        this.CantidadSinSexo = CantidadSinSexo;
    }

    public float getCantiMasculino() {
        return CantiMasculino;
    }

    public void setCantiMasculino(float CantiMasculino) {
        this.CantiMasculino = CantiMasculino;
    }

    public float getCantiFemenenino() {
        return CantiFemenenino;
    }

    public void setCantiFemenenino(float CantiFemenenino) {
        this.CantiFemenenino = CantiFemenenino;
    }

    public PorcentajedeSexo(float CantiMasculino, float CantiFemenenino , float CantidadSinSexo) {
        this.CantiMasculino = CantiMasculino;
        this.CantiFemenenino = CantiFemenenino;
        this.CantidadSinSexo  =CantidadSinSexo;
    }

}

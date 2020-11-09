package model;

import java.util.Date;

public class NotaFiscal {
    
    private int idNotaFiscal;
    private int numeroNota;
    private double valorNota;
    private Date data;
    
    public NotaFiscal(){
    
    }

    public int getIdNotaFiscal() {
        return idNotaFiscal;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setIdNotaFiscal(int idNotaFiscal) {
        this.idNotaFiscal = idNotaFiscal;
    }

    public int getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(int numeroNota) {
        this.numeroNota = numeroNota;
    }

    public double getValorNota() {
        return valorNota;
    }

    public void setValorNota(double valorNota) {
        this.valorNota = valorNota;
    }
    
}

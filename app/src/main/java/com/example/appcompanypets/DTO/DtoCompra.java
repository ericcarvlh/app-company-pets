package com.example.appcompanypets.DTO;


import java.util.ArrayList;
import java.util.List;

public class DtoCompra
{
    private int cd_Compra;
    private int cd_FormaPagamento;
    private int cd_Usuario;
    private double vl_TotalCompra;
    private String ds_Status;

    public int getCd_Compra() {
        return cd_Compra;
    }

    public void setCd_Compra(int cd_Compra) {
        this.cd_Compra = cd_Compra;
    }

    public int getCd_FormaPagamento() {
        return cd_FormaPagamento;
    }

    public void setCd_FormaPagamento(int cd_FormaPagamento) {
        this.cd_FormaPagamento = cd_FormaPagamento;
    }

    public int getCd_Usuario() {
        return cd_Usuario;
    }

    public void setCd_Usuario(int cd_Usuario) {
        this.cd_Usuario = cd_Usuario;
    }

    public double getVl_TotalCompra() {
        return vl_TotalCompra;
    }

    public void setVl_TotalCompra(double vl_TotalCompra) {
        this.vl_TotalCompra = vl_TotalCompra;
    }

    public String getDs_Status() {
        return ds_Status;
    }

    public void setDs_Status(String ds_Status) {
        this.ds_Status = ds_Status;
    }

    public static ArrayList<DtoProduto> ItensCarrinho = new ArrayList<DtoProduto>();
}

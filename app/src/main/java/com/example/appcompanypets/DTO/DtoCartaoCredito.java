package com.example.appcompanypets.DTO;

import java.util.Date;

public class DtoCartaoCredito
{
    private int cd_Cartao;
    private int cd_Pagamento;
    private String nm_Cartao;
    private String no_Cartao;
    private String no_CVV;
    private String no_Parcelas;
    private Date dt_MesValidade;
    private Date dt_AnoValidade;
    private String ds_Status;

    public int getCd_Cartao() {
        return cd_Cartao;
    }

    public void setCd_Cartao(int cd_Cartao) {
        this.cd_Cartao = cd_Cartao;
    }

    public int getCd_Pagamento() {
        return cd_Pagamento;
    }

    public void setCd_Pagamento(int cd_Pagamento) {
        this.cd_Pagamento = cd_Pagamento;
    }

    public String getNm_Cartao() {
        return nm_Cartao;
    }

    public void setNm_Cartao(String nm_Cartao) {
        this.nm_Cartao = nm_Cartao;
    }

    public String getNo_Cartao() {
        return no_Cartao;
    }

    public void setNo_Cartao(String no_Cartao) {
        this.no_Cartao = no_Cartao;
    }

    public String getNo_CVV() {
        return no_CVV;
    }

    public void setNo_CVV(String no_CVV) {
        this.no_CVV = no_CVV;
    }

    public String getNo_Parcelas() {
        return no_Parcelas;
    }

    public void setNo_Parcelas(String no_Parcelas) {
        this.no_Parcelas = no_Parcelas;
    }

    public Date getDt_MesValidade() {
        return dt_MesValidade;
    }

    public void setDt_MesValidade(Date dt_MesValidade) {
        this.dt_MesValidade = dt_MesValidade;
    }

    public Date getDt_AnoValidade() {
        return dt_AnoValidade;
    }

    public void setDt_AnoValidade(Date dt_AnoValidade) {
        this.dt_AnoValidade = dt_AnoValidade;
    }

    public String getDs_Status() {
        return ds_Status;
    }

    public void setDs_Status(String ds_Status) {
        this.ds_Status = ds_Status;
    }
}

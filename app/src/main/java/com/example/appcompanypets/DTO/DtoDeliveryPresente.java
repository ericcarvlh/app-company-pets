package com.example.appcompanypets.DTO;

public class DtoDeliveryPresente
{
    private int cd_DeliveryPresente;
    private int cd_Entrega;
    private String ds_UF;
    private String nm_Cidade;
    private String nm_Bairro;
    private String nm_Logradouro;
    private String no_Logradouro;
    private String no_CEP;
    private String ds_Complemento;
    private String ds_Status;

    public int getCd_DeliveryPresente() {
        return cd_DeliveryPresente;
    }

    public void setCd_DeliveryPresente(int cd_DeliveryPresente) {
        this.cd_DeliveryPresente = cd_DeliveryPresente;
    }

    public int getCd_Entrega() {
        return cd_Entrega;
    }

    public void setCd_Entrega(int cd_Entrega) {
        this.cd_Entrega = cd_Entrega;
    }

    public String getDs_UF() {
        return ds_UF;
    }

    public void setDs_UF(String ds_UF) {
        this.ds_UF = ds_UF;
    }

    public String getNm_Cidade() {
        return nm_Cidade;
    }

    public void setNm_Cidade(String nm_Cidade) {
        this.nm_Cidade = nm_Cidade;
    }

    public String getNm_Bairro() {
        return nm_Bairro;
    }

    public void setNm_Bairro(String nm_Bairro) {
        this.nm_Bairro = nm_Bairro;
    }

    public String getNm_Logradouro() {
        return nm_Logradouro;
    }

    public void setNm_Logradouro(String nm_Logradouro) {
        this.nm_Logradouro = nm_Logradouro;
    }

    public String getNo_Logradouro() {
        return no_Logradouro;
    }

    public void setNo_Logradouro(String no_Logradouro) {
        this.no_Logradouro = no_Logradouro;
    }

    public String getNo_CEP() {
        return no_CEP;
    }

    public void setNo_CEP(String no_CEP) {
        this.no_CEP = no_CEP;
    }

    public String getDs_Complemento() {
        return ds_Complemento;
    }

    public void setDs_Complemento(String ds_Complemento) {
        this.ds_Complemento = ds_Complemento;
    }

    public String getDs_Status() {
        return ds_Status;
    }

    public void setDs_Status(String ds_Status) {
        this.ds_Status = ds_Status;
    }
}

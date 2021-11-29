package com.example.appcompanypets.DTO;

public class DtoDeliveryCliente
{
    private int cd_DeliveryCliente;
    private int cd_Entrega;
    private String ds_Status;

    public int getCd_DeliveryCliente() {
        return cd_DeliveryCliente;
    }

    public void setCd_DeliveryCliente(int cd_DeliveryCliente) {
        this.cd_DeliveryCliente = cd_DeliveryCliente;
    }

    public int getCd_Entrega() {
        return cd_Entrega;
    }

    public void setCd_Entrega(int cd_Entrega) {
        this.cd_Entrega = cd_Entrega;
    }

    public String getDs_Status() {
        return ds_Status;
    }

    public void setDs_Status(String ds_Status) {
        this.ds_Status = ds_Status;
    }
}

package com.example.appcompanypets.DAO;

import com.example.appcompanypets.DTO.DtoCompra;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DaoCompra
{
    @GET("realizaCompra.php")
    Call<ArrayList<DtoCompra>> realizaCadastroCompra(
            @Query("formaPagamento") String formaPagamento,
            @Query("tipoEntrega") String tipoEntrega,
            @Query("cd_Usuario") int cd_Usuario,
            @Query("vl_TotalItens") double vl_TotalItens,
            @Query("dt_Delivery") String dt_Delivery,
            @Query("nm_Cartao") String nm_Cartao,
            @Query("no_Cartao") String no_Cartao,
            @Query("no_CVV") String no_CVV,
            @Query("no_Parcelas") String no_Parcelas,
            @Query("dt_MesValidade") String dt_MesValidade,
            @Query("dt_AnoValidade") String dt_AnoValidade,
            @Query("ds_UF") String UFEstado,
            @Query("nm_Cidade") String cidade,
            @Query("nm_Bairro") String bairro,
            @Query("nm_Logradouro") String logradouro,
            @Query("no_Logradouro") String numero,
            @Query("no_CEP") String CEP,
            @Query("ds_Complemento") String complemento
    );

    @GET("insereItensCompra.php")
    Call<Boolean> insereItensCompra(
            @Query("cd_Produto") int cd_Produto,
            @Query("qt_Itens") int qt_Itens,
            @Query("vl_TotalItens") double vl_TotalItens,
            @Query("cd_Compra") int cd_Compra
    );
}

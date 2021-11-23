package com.example.appcompanypets.Dao;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DaoUsuario
{
    @FormUrlEncoded
    @POST("cadastrarUsuario.php")
    Call<Boolean> cadastrar(
//            @Field("") String nome,
//            @Field("") String siglaSexo,
//            @Field("") String telefone,
//            @Field("") String celular,
//            @Field("") String CPF,
//            @Field("") String CEP,
//            @Field("") String UFEstado,
//            @Field("") String cidade,
//            @Field("") String email,
//            @Field("") String senha,
//            @Field("") String numero,
//            @Field("") String logradouro,
//            @Field("") String dataNascimento,
//            @Field("") String bairro,
//            @Field("") String complemento
    );

}

package com.example.appcompanypets.Dao;

import com.example.appcompanypets.Dto.DtoUsuario;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DaoUsuario
{
    @FormUrlEncoded
    @POST("cadastroUsuario.php")
    Call<Boolean> cadastrar(
            @Field("nm_Usuario") String nome,
            @Field("dt_Nascimento") String dataNascimento,
            @Field("ds_Senha") String senha,
            @Field("sg_Sexo") String siglaSexo,
            @Field("no_CPF") String CPF,
            @Field("ds_Email") String email,
            @Field("no_UF") String UFEstado,
            @Field("nm_Cidade") String cidade,
            @Field("nm_Bairro") String bairro,
            @Field("nm_Logradouro") String logradouro,
            @Field("no_Logradouro") String numero,
            @Field("no_CEP") String CEP,
            @Field("ds_Complemento") String complemento,
            @Field("no_Telefone") String telefone,
            @Field("no_Celular") String celular
    );

    @GET("consultaLogin.php")
    Call<DtoUsuario> Login(
            @Query("ds_Email") String email,
            @Query("ds_Senha") String senha
    );
}

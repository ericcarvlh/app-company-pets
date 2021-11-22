package com.example.appcompanypets.Dao;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DaoLogin
{
    @FormUrlEncoded
    @POST("loginUsuario.php")
    Call<Boolean> login(
            @Field("") String email,
            @Field("") String senha
    );
}

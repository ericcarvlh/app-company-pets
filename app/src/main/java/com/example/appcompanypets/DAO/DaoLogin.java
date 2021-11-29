package com.example.appcompanypets.DAO;

import com.example.appcompanypets.DTO.DtoUsuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DaoLogin
{
    @GET("consultaLogin.php")
    Call<ArrayList<DtoUsuario>> loginUsuario(
            @Query("ds_Email") String email,
            @Query("ds_Senha") String senha
    );
}

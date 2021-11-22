package com.example.appcompanypets.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit
{
    private static final String URL_PRINCIPAL = "";

    public static Retrofit retrofit;

    public static Retrofit getRetrofit()
    {
        if(retrofit!=null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_PRINCIPAL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }
}
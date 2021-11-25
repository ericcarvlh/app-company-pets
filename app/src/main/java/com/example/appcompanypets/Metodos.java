package com.example.appcompanypets;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.appcompanypets.Dto.DtoUsuario;
import com.example.appcompanypets.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Metodos extends AppCompatActivity
{
    public void transicaoFragment(Fragment fragment, Bundle args, FragmentActivity context, int layout)
    {
        fragment.setArguments(args);
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        transaction.replace(layout, fragment);
        transaction.commit();
    }

    public void abreSomenteTelaBotao(Button button, Context telaAtual, Class<?> telaAAbrir)
    {
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(telaAtual, telaAAbrir);
                startActivity(intent);
            }
        });
    }

    public void retrofitProcedimento(Call<Boolean> call, String responstaSucesso, String responstaErro, String falha, Context tela, Class<?> telaDesejada) {
        call.enqueue(new Callback<Boolean>()
        {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response)
            {
                if(response.isSuccessful())
                {
                    Toast.makeText(tela, responstaSucesso, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(tela, telaDesejada);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(tela, responstaErro + response.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable throwable)
            {
                Toast.makeText(tela, falha + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("json", throwable.getMessage());
            }
        });
    }

    public void retrofitProcedimentoLogin(Call<DtoUsuario> call, String responstaSucesso, String responstaErro, String falha, Context tela, Class<?> telaDesejada)
    {
        call.enqueue(new Callback<DtoUsuario>()
        {
            @Override
            public void onResponse(Call<DtoUsuario> call, Response<DtoUsuario> response)
            {
                if(response.isSuccessful())
                {
                    Toast.makeText(tela, responstaSucesso, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(tela, telaDesejada);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(tela, responstaErro + response.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DtoUsuario> call, Throwable throwable)
            {
                Toast.makeText(tela, falha + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("json", throwable.getMessage());
            }
        });
    }
}

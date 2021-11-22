package com.example.appcompanypets;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.appcompanypets.R;

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
}

package com.example.appcompanypets.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.appcompanypets.Fragments.Cadastro.CadastroFragment1;
import com.example.appcompanypets.R;

public class CadastroActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayoutConteudoCadastro, new CadastroFragment1());
        transaction.commit();

    }
}
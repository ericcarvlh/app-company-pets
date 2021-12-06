package com.example.appcompanypets.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

import com.example.appcompanypets.Fragments.cadastro.CadastroFragment1;
import com.example.appcompanypets.Fragments.compra.CompraFragment1;
import com.example.appcompanypets.R;

public class CompraActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayoutConteudoCompra, new CompraFragment1());
        transaction.commit();

    }
}
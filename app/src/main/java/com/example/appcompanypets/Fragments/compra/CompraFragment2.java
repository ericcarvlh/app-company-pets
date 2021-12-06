package com.example.appcompanypets.Fragments.compra;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.appcompanypets.Fragments.cadastro.CadastroFragment1;
import com.example.appcompanypets.Fragments.formaPagamento.CartaoCreditoFragment;
import com.example.appcompanypets.Fragments.formaPagamento.CartaoDebitoFragment;
import com.example.appcompanypets.R;

public class CompraFragment2 extends Fragment
{
    FragmentActivity context;
    ImageButton imageButtonCartaoCredito, imageButtonCartaoDebito;

    public CompraFragment2()
    {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity)
    {
        context = (FragmentActivity) activity;

        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_compra2, container, false);

        imageButtonCartaoCredito = view.findViewById(R.id.imageButtonCartaoCredito);
        imageButtonCartaoDebito = view.findViewById(R.id.imageButtonCartaoDebito);

        imageButtonCartaoCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayoutConteudoFormaPagamento, new CartaoCreditoFragment());
                transaction.commit();
            }
        });

        imageButtonCartaoDebito.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayoutConteudoFormaPagamento, new CartaoDebitoFragment());
                transaction.commit();
            }
        });

        return view;
    }
}
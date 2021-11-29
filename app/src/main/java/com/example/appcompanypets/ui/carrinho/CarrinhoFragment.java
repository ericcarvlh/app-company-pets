package com.example.appcompanypets.ui.carrinho;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.appcompanypets.R;

public class CarrinhoFragment extends Fragment
{
    Button buttonContinuarComprando, buttonComprar;

    public CarrinhoFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_carrinho, container, false);

        buttonContinuarComprando = view.findViewById(R.id.buttonContinuarComprando);
        buttonComprar = view.findViewById(R.id.buttonComprar);

        buttonContinuarComprando.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                startActivity(intent);
            }
        });

        buttonComprar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        return view;
    }
}
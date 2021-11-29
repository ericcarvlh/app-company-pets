package com.example.appcompanypets.ui.produto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appcompanypets.DTO.DtoProduto;
import com.example.appcompanypets.DTO.DtoUsuario;
import com.example.appcompanypets.R;

public class DetalhesProdutoFragment extends Fragment
{
    DtoProduto dto = new DtoProduto();


    public DetalhesProdutoFragment(DtoProduto dto)
    {
        this.dto = dto;
    }

    public DetalhesProdutoFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_detalhes_produto, container, false);

        return view;
    }
}
package com.example.appcompanypets.Fragments.formaPagamento;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appcompanypets.R;

public class CartaoDebitoFragment extends Fragment
{
    public CartaoDebitoFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cartao_debito, container, false);
        return view;
    }
}
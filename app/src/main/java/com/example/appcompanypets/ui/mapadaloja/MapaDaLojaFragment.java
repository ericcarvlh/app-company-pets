package com.example.appcompanypets.ui.mapadaloja;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appcompanypets.R;

public class MapaDaLojaFragment extends Fragment {

    public MapaDaLojaFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_mapa_da_loja, container, false);

        return view;
    }
}
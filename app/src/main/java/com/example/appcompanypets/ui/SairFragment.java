package com.example.appcompanypets.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appcompanypets.Activities.BoasVindasActivity;
import com.example.appcompanypets.Activities.SplashActivity;
import com.example.appcompanypets.DTO.DtoUsuario;
import com.example.appcompanypets.R;

public class SairFragment extends Fragment
{
    TextView textViewSaindo;
    FragmentActivity context;
    CardView cardViewSaindo;

    public SairFragment() {
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
        View view = inflater.inflate(R.layout.fragment_sair, container, false);

        DtoUsuario.cd_UsuLogin = 0;
        Intent intent = new Intent(context, BoasVindasActivity.class);

        try {
            Thread.sleep(2000);
            startActivity(intent);
            context.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return view;
    }
}
package com.example.appcompanypets.Fragments.delivery;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appcompanypets.DTO.DtoUsuario;
import com.example.appcompanypets.Fragments.compra.CompraFragment2;
import com.example.appcompanypets.R;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class DeliveryCliFragment extends Fragment
{
    FragmentActivity context;
    DtoUsuario dtoUsuario = new DtoUsuario();
    Retrofit retrofit;
    String tipoEntrega = "deliveryCli";
    ArrayList<DtoUsuario> arrayListUsuario = new ArrayList<>();

    public DeliveryCliFragment()
    {

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
        View view = inflater.inflate(R.layout.fragment_delivery_cli, container, false);

        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayoutConteudoCompra, new CompraFragment2());
        transaction.commit();

        return view;
    }

}
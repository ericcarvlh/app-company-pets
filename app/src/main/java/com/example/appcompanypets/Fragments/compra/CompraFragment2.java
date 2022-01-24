package com.example.appcompanypets.Fragments.compra;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appcompanypets.DAO.DaoUsuario;
import com.example.appcompanypets.DTO.DtoCartaoCredito;
import com.example.appcompanypets.DTO.DtoCartaoDebito;
import com.example.appcompanypets.DTO.DtoUsuario;
import com.example.appcompanypets.Fragments.delivery.DeliveryPreFragment;
import com.example.appcompanypets.R;
import com.example.appcompanypets.Retrofit.ConfigRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CompraFragment2 extends Fragment
{
    FragmentActivity context;
    String tipoEntrega;
    String formaPagamento;
    DtoCartaoCredito dtoCartaoCredito;
    DtoCartaoDebito dtoCartaoDebito;

    public CompraFragment2(String tipoEntrega, String formaPagamento, DtoCartaoDebito dtoCartaoDebito, DtoCartaoCredito dtoCartaoCredito)
    {
        this.tipoEntrega = tipoEntrega;
        this.formaPagamento = formaPagamento;
        this.dtoCartaoDebito = dtoCartaoDebito;
        this.dtoCartaoCredito = dtoCartaoCredito;
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_compra2, container, false);

        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();

        if (tipoEntrega.equals("deliveryPre"))
            transaction.replace(R.id.frameLayoutConteudoCompra, new DeliveryPreFragment(formaPagamento, dtoCartaoDebito, dtoCartaoCredito));
        else if(tipoEntrega.equals("deliveryCli"))
            transaction.replace(R.id.frameLayoutConteudoCompra, new CompraFragment3(tipoEntrega, formaPagamento, dtoCartaoDebito, dtoCartaoCredito, null));
        else
            transaction.replace(R.id.frameLayoutConteudoCompra, new CompraFragment3(tipoEntrega, formaPagamento, dtoCartaoDebito, dtoCartaoCredito, null));

        transaction.commit();

        return view;
    }
}
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
    DtoUsuario dtoUsuario = new DtoUsuario();
    ArrayList<DtoUsuario> arrayList  = new ArrayList<>();
    Retrofit retrofit;

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

    private void enderecoUsuario(int cd_Usuario)
    {
        retrofit = ConfigRetrofit.getRetrofit();
        DaoUsuario dao = retrofit.create(DaoUsuario.class);
        Call<ArrayList<DtoUsuario>> call = dao.EnderecoUsuario(cd_Usuario);
        call.enqueue(new Callback<ArrayList<DtoUsuario>>()
        {
            @Override
            public void onResponse(Call<ArrayList<DtoUsuario>> call, Response<ArrayList<DtoUsuario>> response)
            {
                arrayList = response.body();
                if(arrayList.size()!=0)
                {
                    dtoUsuario.setNm_Bairro(arrayList.get(0).getNm_Bairro());
                    dtoUsuario.setNo_UF(arrayList.get(0).getNo_UF());
                    dtoUsuario.setNm_Cidade(arrayList.get(0).getNm_Cidade());
                    dtoUsuario.setNm_Bairro(arrayList.get(0).getNm_Bairro());
                    dtoUsuario.setNm_Logradouro(arrayList.get(0).getNm_Logradouro());
                    dtoUsuario.setNo_Logradouro(arrayList.get(0).getNo_Logradouro());
                    dtoUsuario.setNo_CEP(arrayList.get(0).getNo_CEP());
                    dtoUsuario.setDs_Complemento(arrayList.get(0).getDs_Complemento());
                }
                else
                    Toast.makeText(context, "Erro ao executar a compra.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ArrayList<DtoUsuario>> call, Throwable throwable) {

            }
        });
    }
}
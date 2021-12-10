package com.example.appcompanypets.Fragments.formaPagamento;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcompanypets.DTO.DtoCartaoDebito;
import com.example.appcompanypets.Fragments.compra.CompraFragment2;
import com.example.appcompanypets.R;
import com.google.android.material.textfield.TextInputEditText;

public class CartaoDebitoFragment extends Fragment
{
    FragmentActivity context;
    DtoCartaoDebito dto = new DtoCartaoDebito();
    Button buttonContinuar;
    TextInputEditText editTextNomeImpresso, editTextNumeroCartao,
            editTextCVV, editTextMesValidade, editTextAnoValidade;
    String formaPagamento = "Cartão de Débito";
    String tipoEntrega;

    public CartaoDebitoFragment(String tipoEntrega)
    {
        this.tipoEntrega = tipoEntrega;
    }

    public CartaoDebitoFragment()
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
        View view = inflater.inflate(R.layout.fragment_cartao_debito, container, false);

        editTextNomeImpresso = view.findViewById(R.id.editTextNomeImpresso_CartaoDebito);
        editTextNumeroCartao = view.findViewById(R.id.editTextNumero_CartaoDebito);
        editTextCVV = view.findViewById(R.id.editTextCVV_CartaoDebito);
        editTextMesValidade = view.findViewById(R.id.editTextMesValidade_CartaoDebito);
        editTextAnoValidade = view.findViewById(R.id.editTextAnoValidade_CartaoDebito);

        buttonContinuar = view.findViewById(R.id.buttonContinuar_CartaoDebito);

        buttonContinuar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dto.setNm_Cartao(editTextNomeImpresso.getText().toString());
                dto.setNo_Cartao(editTextNumeroCartao.getText().toString());
                dto.setNo_CVV(editTextCVV.getText().toString());
                dto.setDt_AnoValidade(editTextAnoValidade.getText().toString());
                dto.setDt_MesValidade(editTextMesValidade.getText().toString());

                if (dto!=null && tipoEntrega!=null)
                {
                    FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frameLayoutConteudoCompra, new CompraFragment2(tipoEntrega, formaPagamento, dto, null));
                    transaction.commit();
                }
                else
                    Toast.makeText(context, "Há campos nulos", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}
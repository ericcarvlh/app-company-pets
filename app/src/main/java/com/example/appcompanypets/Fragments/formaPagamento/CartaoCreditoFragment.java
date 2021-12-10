package com.example.appcompanypets.Fragments.formaPagamento;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appcompanypets.DTO.DtoCartaoCredito;
import com.example.appcompanypets.Fragments.compra.CompraFragment2;
import com.example.appcompanypets.R;
import com.google.android.material.textfield.TextInputEditText;

public class CartaoCreditoFragment extends Fragment
{
    FragmentActivity context;
    DtoCartaoCredito dto = new DtoCartaoCredito();
    Button buttonContinuar;
    Spinner spinnerParcela;
    TextInputEditText editTextNomeImpresso, editTextNumeroCartao, editTextCVV, editTextMesValidade, editTextAnoValidade;
    String formaPagamento = "Cartão de Crédito";
    String tipoEntrega;

    public CartaoCreditoFragment(String tipoEntrega)
    {
        this.tipoEntrega = tipoEntrega;
    }

    public CartaoCreditoFragment()
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
        View view = inflater.inflate(R.layout.fragment_cartao_credito, container, false);

        editTextNomeImpresso = view.findViewById(R.id.editTextNomeImpresso_CartaoCredito);
        editTextNumeroCartao = view.findViewById(R.id.editTextNumero_CartaoCredito);
        editTextCVV = view.findViewById(R.id.editTextCVV_CartaoCredito);
        editTextMesValidade = view.findViewById(R.id.editTextMesValidade_CartaoCredito);
        editTextAnoValidade = view.findViewById(R.id.editTextAnoValidade_CartaoCredito);

        buttonContinuar = view.findViewById(R.id.buttonContinuar_CartaoCredito);

        spinnerParcela = view.findViewById(R.id.spinnerParcela_CartaoCredito);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_parcelamento, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerParcela.setAdapter(adapter);

        spinnerParcela.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (position>0)
                    dto.setNo_Parcelas(position+"");
                else
                    dto.setNo_Parcelas("0");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

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
                    transaction.replace(R.id.frameLayoutConteudoCompra, new CompraFragment2(tipoEntrega, formaPagamento, null, dto));
                    transaction.commit();
                }
                else
                    Toast.makeText(context, "Há campos nulos.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}